package org.iass.user.service

import io.jsonwebtoken.Claims
import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException
import org.iass.openfeign.apple.AppleApiClient
import org.iass.openfeign.apple.verify.AppleClaimsValidator
import org.iass.openfeign.apple.verify.AppleJwtParser
import org.iass.openfeign.apple.verify.PublicKeyGenerator
import org.springframework.stereotype.Service

@Service
class AppleLoginService(
	private val appleApiClient: AppleApiClient,
	private val appleJwtParser: AppleJwtParser,
	private val publicKeyGenerator: PublicKeyGenerator,
	private val appleClaimsValidator: AppleClaimsValidator
) {
	fun getAppleId(identityToken: String): String {
		val headers: Map<String, String> = appleJwtParser.parseHeaders(identityToken)
		val applePublicKeys = appleApiClient.getApplePublicKeys()
		val publicKey = publicKeyGenerator.generatePublicKey(headers, applePublicKeys)

		val claims = appleJwtParser.parsePublicKeyAndGetClaims(identityToken, publicKey)
		validateClaims(claims)
		return claims.subject
	}

	private fun validateClaims(claims: Claims) {
		if (!appleClaimsValidator.isValid(claims)) {
			throw CommonException(ErrorType.INVALID_APPLE_CLAIMS)
		}
	}
}
