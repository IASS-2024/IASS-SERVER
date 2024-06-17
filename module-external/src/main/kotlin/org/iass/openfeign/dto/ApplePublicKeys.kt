package org.iass.openfeign.dto

import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException

data class ApplePublicKeys(
	val keys: List<ApplePublicKey>
) {
	fun getMatchesKey(
		alg: String?,
		kid: String?
	): ApplePublicKey =
		keys.firstOrNull { it.alg == alg && it.kid == kid }
			?: throw CommonException(ErrorType.INVALID_APPLE_PUBLIC_KEY)
}
