package org.iass.exception

import org.iass.dto.response.ErrorType

class UnauthorizedException : CommonException {
	constructor() : super(ErrorType.UNAUTHORIZED)

	constructor(errorType: ErrorType) : super(errorType)
}
