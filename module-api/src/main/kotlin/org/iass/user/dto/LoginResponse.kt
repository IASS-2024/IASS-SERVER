package org.iass.user.dto

import org.iass.auth.jwt.TokenResponse

data class LoginResponse(
	val userId: String?,
	val token: TokenResponse
)
