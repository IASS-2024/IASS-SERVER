package org.iass.openfeign.dto

import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException
import java.util.function.Predicate

data class ApplePublicKeys(
	val keys: List<ApplePublicKey>
) {
	fun getMatchesKey(alg: String?, kid: String?): ApplePublicKey {
		return keys
			.stream()
			.filter(Predicate<ApplePublicKey> { k: ApplePublicKey -> k.alg == alg && k.kid == kid })
			.findFirst()
			.orElseThrow<CommonException> { CommonException(ErrorType.INVALID_APPLE_PUBLIC_KEY) }
	}
}
