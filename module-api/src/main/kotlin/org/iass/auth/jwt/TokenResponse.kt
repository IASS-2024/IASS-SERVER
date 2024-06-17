package org.iass.auth.jwt

data class TokenResponse(
	var accessToken: String,
	var refreshToken: String
) {
	init {
		this.accessToken = "Bearer $accessToken"
		this.refreshToken = "Bearer $refreshToken"
	}
}
