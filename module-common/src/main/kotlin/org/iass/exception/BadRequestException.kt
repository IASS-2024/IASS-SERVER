package org.iass.exception

import org.iass.dto.response.ErrorType

class BadRequestException : CommonException {
	constructor() : super(ErrorType.BAD_REQUEST)

	constructor(errorType: ErrorType) : super(errorType)
}
