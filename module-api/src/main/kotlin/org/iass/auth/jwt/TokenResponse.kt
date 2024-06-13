package org.iass.auth.jwt

data class TokenResponse(val accessToken: String,
						 val refreshToken: String
) {
	companion object {
		fun of(accessToken: String, refreshToken: String): TokenResponse {
			return TokenResponse("Bearer $accessToken", "Bearer $refreshToken")
		}
	}
}
