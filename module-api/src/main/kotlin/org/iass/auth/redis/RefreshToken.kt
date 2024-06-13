package org.iass.auth.redis

import org.iass.auth.jwt.JwtTokenProvider
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "refreshToken", timeToLive = JwtTokenProvider.JWTConstants.REFRESH_TOKEN_EXPIRATION_TIME)
data class RefreshToken (
	@Id
	private val id: Long? = null,
	private val refreshToken: String? = null
)
