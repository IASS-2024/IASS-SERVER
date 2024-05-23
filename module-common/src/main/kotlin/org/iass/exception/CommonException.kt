package org.iass.exception

import org.iass.dto.response.ErrorType

open class CommonException(
	val errorType: ErrorType
) : RuntimeException(errorType.message) {
	fun getHttpStatus() = errorType.status
}
