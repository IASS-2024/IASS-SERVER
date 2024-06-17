package org.iass.openfeign.apple.verify

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import org.iass.dto.response.ErrorType
import org.iass.exception.BadRequestException
import org.springframework.stereotype.Component
import java.security.PublicKey

@Component
class AppleJwtParser(
	private val OBJECT_MAPPER: ObjectMapper
) {
	fun parseHeaders(identityToken: String): Map<String, String> {
		try {
			val encodedHeader = identityToken.split(".")[HEADER_INDEX]
			val decodedHeader =
				String(
					java.util.Base64
						.getUrlDecoder()
						.decode(encodedHeader)
				)
			val typeRef = object : com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {}
			return OBJECT_MAPPER.readValue(decodedHeader, typeRef)
		} catch (e: JsonProcessingException) {
			throw BadRequestException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: ArrayIndexOutOfBoundsException) {
			throw BadRequestException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: IllegalArgumentException) {
			throw BadRequestException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		}
	}

	fun parsePublicKeyAndGetClaims(
		idToken: String?,
		publicKey: PublicKey?
	): Claims {
		try {
			return Jwts
				.parserBuilder()
				.setSigningKey(publicKey)
				.build()
				.parseClaimsJws(idToken)
				.body
		} catch (e: ExpiredJwtException) {
			throw BadRequestException(ErrorType.EXPIRED_APPLE_IDENTITY_TOKEN)
		} catch (e: UnsupportedJwtException) {
			throw BadRequestException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: MalformedJwtException) {
			throw BadRequestException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		} catch (e: IllegalArgumentException) {
			throw BadRequestException(ErrorType.INVALID_APPLE_IDENTITY_TOKEN)
		}
	}

	companion object {
		private const val IDENTITY_TOKEN_VALUE_DELIMITER = "\\."
		private const val HEADER_INDEX = 0
	}
}
