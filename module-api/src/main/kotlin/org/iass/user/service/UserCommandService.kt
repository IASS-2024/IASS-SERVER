package org.iass.user.service

import org.iass.auth.jwt.TokenResponse
import org.iass.user.dto.request.LoginRequest
import org.iass.user.dto.request.SignInRequest
import org.iass.user.dto.response.LoginResponse

interface UserCommandService {
	fun login(
		authorization: String,
		request: LoginRequest
	): LoginResponse

	fun signIn(
		userId: String,
		request: SignInRequest
	)

	fun reissue(authorization: String): TokenResponse

	fun logout(userId: String)

	fun withdrawal(userId: String)
}
