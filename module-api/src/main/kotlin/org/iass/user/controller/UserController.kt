package org.iass.user.controller

import jakarta.validation.Valid
import org.iass.auth.jwt.JwtTokenProvider
import org.iass.auth.jwt.TokenResponse
import org.iass.dto.response.ApiResponse
import org.iass.dto.response.SuccessType
import org.iass.user.dto.request.LoginRequest
import org.iass.user.dto.response.LoginResponse
import org.iass.user.dto.request.SignInRequest
import org.iass.user.facade.UserFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
		return ResponseEntity.ok(ApiResponse.success(SuccessType.POST_APPLE_LOGIN_SUCCESS, userFacade.login(authorization, request)))
	}

	@PatchMapping("/signIn")
	fun signIn(
		principal: Principal,
		@Valid @RequestBody request: SignInRequest): ResponseEntity<ApiResponse<Nothing>> {
		userFacade.signIn(jwtTokenProvider.getUserIdFromPrincipal(principal), request)
		return ResponseEntity.ok(ApiResponse.success(SuccessType.PATCH_SIGN_IN_SUCCESS))
	}

	@PostMapping("/reissue")
	fun reissue(
		@RequestHeader("Authorization") authorization: String) : ResponseEntity<ApiResponse<TokenResponse>>  {
		return ResponseEntity.ok(ApiResponse.success(SuccessType.POST_REISSUE_SUCCESS, userFacade.reissue(authorization)))

	}

	@PostMapping("/logout")
	fun logout(
		principal: Principal) : ResponseEntity<ApiResponse<Nothing>> {
		userFacade.logout(jwtTokenProvider.getUserIdFromPrincipal(principal))
		return ResponseEntity.ok(ApiResponse.success(SuccessType.POST_LOGOUT_SUCCESS))
	}

	@DeleteMapping("/withdrawal")
	fun withdrawal(
		principal: Principal) : ResponseEntity<ApiResponse<Nothing>> {
		userFacade.withdrawal(jwtTokenProvider.getUserIdFromPrincipal(principal))
		return ResponseEntity.ok(ApiResponse.success(SuccessType.SUCCESS_WITHDRAWAL))
	}
}
