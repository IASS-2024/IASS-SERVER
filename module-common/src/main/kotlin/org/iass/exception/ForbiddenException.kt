package org.iass.exception

import org.iass.dto.response.ErrorType

class ForbiddenException : CommonException {
	constructor() : super(ErrorType.FORBIDDEN)

	constructor(errorType: ErrorType) : super(errorType)
}
