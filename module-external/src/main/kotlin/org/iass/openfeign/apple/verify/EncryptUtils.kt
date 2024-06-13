package org.iass.openfeign.apple.verify

import org.iass.dto.response.ErrorType
import org.iass.exception.BadRequestException
import org.iass.exception.CommonException
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class EncryptUtils {
	fun encrypt(value: String): String {
		try {
			val sha256: MessageDigest = MessageDigest.getInstance("SHA-256")
			val digest: ByteArray = sha256.digest(value.toByteArray(StandardCharsets.UTF_8))
			val hexString = StringBuilder()
			for (b in digest) {
				hexString.append(String.format("%02x", b))
			}
			return hexString.toString()
		} catch (e: NoSuchAlgorithmException) {
			throw BadRequestException(ErrorType.INVALID_ENCRYPT_COMMUNICATION)
		}
	}
}
