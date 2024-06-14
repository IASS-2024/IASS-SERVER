package org.iass.user.facade

import org.iass.auth.jwt.TokenResponse
import org.iass.auth.redis.RefreshToken
import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.iass.user.dto.SignInRequest
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
	fun login(authorization: String, request: LoginRequest): LoginResponse {
		return userCommandService.login(authorization, request)
	}

	fun signIn(userId: String, request: SignInRequest) {
		return userCommandService.signIn(userId, request)
	}

	fun reissue(authorization: String): TokenResponse{
		return userCommandService.reissue(authorization)
	}

	fun logout(userId: String) {
		return userCommandService.logout(userId)
	}

	fun withdrawal(userId: String) {
		return userCommandService.withdrawal(userId)
	}
}
