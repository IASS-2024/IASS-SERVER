package org.iass.user.dto.response

import org.iass.auth.jwt.TokenResponse

data class LoginResponse(
	val userId: String?,
	val token: TokenResponse
)
