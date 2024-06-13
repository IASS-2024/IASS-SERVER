package org.iass.user.service

import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse

interface UserCommandService {
	fun login(authorization: String, request: LoginRequest): LoginResponse
}
