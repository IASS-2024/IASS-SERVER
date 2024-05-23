package org.iass.exception

import org.iass.dto.response.ErrorType

class NotFoundException : CommonException {
	constructor() : super(ErrorType.NOT_FOUND)

	constructor(errorType: ErrorType) : super(errorType)
}
