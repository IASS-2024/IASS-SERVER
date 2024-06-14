package org.iass.user.dto.response

import org.iass.auth.jwt.TokenResponse

data class LoginResponse(
	val userId: String?,
	val token: TokenResponse
) {
	companion object {
		fun of(userId: String?, token: TokenResponse): LoginResponse {
			return LoginResponse(userId, TokenResponse.of(token.accessToken, token.refreshToken))
		}
	}
}
