package org.iass.user.facade

import org.iass.dto.response.ApiResponse
import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.iass.user.service.UserCommandService
import org.iass.user.service.UserQueryService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserFacedeImpl(
	private val userCommandService: UserCommandService,
	private val userQueryService: UserQueryService
) : UserFacade {
	override fun login(authorization: String, request: LoginRequest): LoginResponse {
		return userCommandService.login(authorization, request)
	}
}
