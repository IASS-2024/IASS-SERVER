package org.iass.user.controller

import jakarta.validation.Valid
import org.iass.auth.jwt.JwtTokenProvider
import org.iass.dto.response.ApiResponse
import org.iass.dto.response.SuccessType
import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.iass.user.dto.SignInRequest
import org.iass.user.facade.UserFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController (
	private val userFacade: UserFacade,
	private val jwtTokenProvider: JwtTokenProvider
) {
	@PostMapping("/login")
	fun login(
		@RequestHeader("Authorization") authorization: String,
		@Valid @RequestBody request: LoginRequest): ResponseEntity<ApiResponse<LoginResponse>> {
		return ResponseEntity.ok(ApiResponse.success(SuccessType.SUCCESS_APPLE_LOGIN, userFacade.login(authorization, request)))
	}

	@PatchMapping("/signIn")
	fun signIn(
		principal: Principal, @RequestBody request: SignInRequest): ResponseEntity<ApiResponse<Nothing>> {
		userFacade.signIn(jwtTokenProvider.getUserIdFromPrincipal(principal), request)
		return ResponseEntity.ok(ApiResponse.success(SuccessType.SUCCESS_SIGNIN))
	}
}
