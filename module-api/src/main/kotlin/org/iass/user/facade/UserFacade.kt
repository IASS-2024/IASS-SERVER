package org.iass.user.facade

import org.iass.auth.jwt.TokenResponse
import org.iass.user.dto.request.LoginRequest
import org.iass.user.dto.request.SignInRequest
import org.iass.user.dto.response.LoginResponse
import org.iass.user.service.UserCommandService
import org.iass.user.service.UserQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserFacade(
	private val userCommandService: UserCommandService,
	private val userQueryService: UserQueryService
) {
	fun login(
		authorization: String,
		request: LoginRequest
	): LoginResponse = userCommandService.login(authorization, request)

	fun signIn(
		userId: String,
		request: SignInRequest
	) = userCommandService.signIn(userId, request)

	fun reissue(authorization: String): TokenResponse = userCommandService.reissue(authorization)

	fun logout(userId: String) = userCommandService.logout(userId)

	fun withdrawal(userId: String) = userCommandService.withdrawal(userId)
}
