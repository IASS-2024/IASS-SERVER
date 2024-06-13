package org.iass.openfeign.apple.verify

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException
import org.springframework.stereotype.Component
import java.security.PublicKey

@Component
class AppleJwtParser(
	private val OBJECT_MAPPER: ObjectMapper
) {
	fun parseHeaders(identityToken: String): Map<String, String> {
		try {
			val encodedHeader = identityToken.split(IDENTITY_TOKEN_VALUE_DELIMITER.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[HEADER_INDEX]
			val decodedHeader = String(java.util.Base64.getUrlDecoder().decode(encodedHeader))
			val typeRef = object : com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {}
			return OBJECT_MAPPER.readValue(decodedHeader, typeRef)
		} catch (e: JsonProcessingException) {
			throw CommonException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: ArrayIndexOutOfBoundsException) {
			throw CommonException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		}
	}

	fun parsePublicKeyAndGetClaims(idToken: String?, publicKey: PublicKey?): Claims {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(publicKey)
				.build()
				.parseClaimsJws(idToken)
				.body
		} catch (e: ExpiredJwtException) {
			throw CommonException(ErrorType.EXPIRED_APPLE_IDENTITY_TOKEN)
		} catch (e: UnsupportedJwtException) {
			throw CommonException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: MalformedJwtException) {
			throw CommonException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: IllegalArgumentException) {
			throw CommonException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		}
	}

	companion object {
		private const val IDENTITY_TOKEN_VALUE_DELIMITER = "\\."
		private const val HEADER_INDEX = 0
	}
}
