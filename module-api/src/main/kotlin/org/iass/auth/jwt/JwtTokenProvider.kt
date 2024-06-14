package org.iass.auth.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import jakarta.annotation.PostConstruct
import org.iass.dto.response.ErrorType
import org.iass.exception.UnauthorizedException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Principal
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey

@Component
class JwtTokenProvider (
	private val redisTemplate: RedisTemplate<String, String>
) {
	@Value("\${jwt.secret}")
	private var JWT_SECRET: String = ""

	@PostConstruct
	protected fun init() {
		JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(StandardCharsets.UTF_8))
	}

	fun reissuedToken(userId: String): TokenResponse {
		return TokenResponse.of(
			generateAccessToken(userId),
			generateRefreshToken(userId))
	}

	fun generateAccessToken(userId: String?): String {
		val now = Date()
		val claims = Jwts.claims()
			.setIssuedAt(now)
			.setExpiration(Date(now.time + JWTConstants.ACCESS_TOKEN_EXPIRATION_TIME))

		claims[JWTConstants.USER_ID] = userId
		claims[JWTConstants.TOKEN_TYPE] = JWTConstants.ACCESS_TOKEN

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.signWith(signingKey)
			.compact()
	}

	fun generateRefreshToken(userId: String?): String {
		val now = Date()
		val claims = Jwts.claims()
			.setIssuedAt(now)
			.setExpiration(Date(now.time + JWTConstants.REFRESH_TOKEN_EXPIRATION_TIME))

		claims[JWTConstants.USER_ID] = userId
		claims[JWTConstants.TOKEN_TYPE] = JWTConstants.REFRESH_TOKEN

		val refreshToken = Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.signWith(signingKey)
			.compact()

		redisTemplate.opsForValue()[userId.toString(), refreshToken, JWTConstants.REFRESH_TOKEN_EXPIRATION_TIME] = TimeUnit.MILLISECONDS
		return refreshToken
	}

	private val signingKey: SecretKey
		get() {
			val encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray())
			return Keys.hmacShaKeyFor(encodedKey.toByteArray())
		}

	fun validateRefreshToken(refreshToken: String?): String {
		validateToken(refreshToken)
		val userId = getUserFromJwt(refreshToken)
		if (redisTemplate.hasKey(userId.toString())) {
			return userId
		} else {
			throw UnauthorizedException()
		}
	}

	fun validateToken(token: String?): JwtValidationType {
		try {
			val claims = getBody(token)
			if (claims[JWTConstants.TOKEN_TYPE].toString() == JWTConstants.ACCESS_TOKEN) {
				return JwtValidationType.VALID_ACCESS
			} else if (claims[JWTConstants.TOKEN_TYPE].toString() == JWTConstants.REFRESH_TOKEN) {
				return JwtValidationType.VALID_REFRESH
			}
			throw UnauthorizedException(ErrorType.WRONG_TYPE_TOKEN)
		} catch (e: MalformedJwtException) {
			throw UnauthorizedException(ErrorType.WRONG_TYPE_TOKEN)
		} catch (e: ExpiredJwtException) {
			throw UnauthorizedException(ErrorType.EXPIRED_TOKEN)
		} catch (e: IllegalArgumentException) {
			throw UnauthorizedException(ErrorType.UNKNOWN_TOKEN)
		} catch (e: UnsupportedJwtException) {
			throw UnauthorizedException(ErrorType.UNSUPPORTED_TOKEN)
		} catch (e: SignatureException) {
			throw UnauthorizedException(ErrorType.WRONG_SIGNATURE_TOKEN)
		}
	}

	fun deleteRefreshToken(userId: String) {
		if (redisTemplate.hasKey(userId)) {
			val valueOperations = redisTemplate.opsForValue()
			val refreshToken = valueOperations[userId]
			redisTemplate.delete(refreshToken!!)
		}
	}

	private fun getBody(token: String?): Claims {
		return Jwts.parserBuilder()
			.setSigningKey(signingKey)
			.build()
			.parseClaimsJws(token)
			.body
	}

	fun getUserFromJwt(token: String?): String {
		val claims = getBody(token)
		return claims[JWTConstants.USER_ID].toString()
	}

	fun getUserIdFromPrincipal(principal: Principal): String {
		if (Objects.isNull(principal)) {
			throw UnauthorizedException(ErrorType.INVALID_AUTH);
		}
		return principal.name
	}

	companion object JWTConstants {
		const val USER_ID: String = "userId"
		const val TOKEN_TYPE: String = "type"
		const val ACCESS_TOKEN: String = "access"
		const val REFRESH_TOKEN: String = "refresh"
		const val ACCESS_TOKEN_EXPIRATION_TIME: Long = 60 * 1000L * 60 * 24 * 7 * 2 // TODO - 만료시간 변경
		const val REFRESH_TOKEN_EXPIRATION_TIME: Long = 60 * 1000L * 60 * 24 * 7 * 2
	}

	enum class JwtValidationType {
		VALID_ACCESS,
		VALID_REFRESH
	}
}
