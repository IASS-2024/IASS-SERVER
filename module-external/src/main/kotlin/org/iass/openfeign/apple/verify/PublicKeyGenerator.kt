package org.iass.openfeign.apple.verify

import org.iass.dto.response.ErrorType
import org.iass.exception.BadRequestException
import org.iass.openfeign.dto.ApplePublicKey
import org.iass.openfeign.dto.ApplePublicKeys
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.RSAPublicKeySpec
import java.util.*

@Component
class PublicKeyGenerator {
	fun generatePublicKey(
		headers: Map<String, String>,
		applePublicKeys: ApplePublicKeys
	): PublicKey {
		val applePublicKey =
			applePublicKeys.getMatchesKey(headers[SIGN_ALGORITHM_HEADER_KEY], headers[KEY_ID_HEADER_KEY])

		return generatePublicKeyWithApplePublicKey(applePublicKey)
	}

	private fun generatePublicKeyWithApplePublicKey(publicKey: ApplePublicKey): PublicKey {
		val nBytes = Base64.getUrlDecoder().decode(publicKey.n)
		val eBytes = Base64.getUrlDecoder().decode(publicKey.e)

		val n = BigInteger(POSITIVE_SIGN_NUMBER, nBytes)
		val e = BigInteger(POSITIVE_SIGN_NUMBER, eBytes)

		val publicKeySpec = RSAPublicKeySpec(n, e)

		try {
			val keyFactory: KeyFactory = KeyFactory.getInstance(publicKey.kty)
			return keyFactory.generatePublic(publicKeySpec)
		} catch (e: NoSuchAlgorithmException) {
			throw BadRequestException(ErrorType.CREATE_PUBLIC_KEY_EXCEPTION)
		} catch (e: InvalidKeySpecException) {
			throw BadRequestException(ErrorType.CREATE_PUBLIC_KEY_EXCEPTION)
		}
	}

	companion object {
		private const val SIGN_ALGORITHM_HEADER_KEY = "alg"
		private const val KEY_ID_HEADER_KEY = "kid"
		private const val POSITIVE_SIGN_NUMBER = 1
	}
}
