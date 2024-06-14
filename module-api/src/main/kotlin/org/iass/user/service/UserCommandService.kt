package org.iass.user.service

import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.iass.user.dto.SignInRequest

interface UserCommandService {
	fun login(authorization: String, request: LoginRequest): LoginResponse
	fun signIn(userId: String, request: SignInRequest)
}
