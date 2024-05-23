package org.iass.exception

import org.iass.dto.response.ErrorType

class InternalServerException : CommonException {
	constructor() : super(ErrorType.INTERNAL_SERVER)

	constructor(errorType: ErrorType) : super(errorType)
}
