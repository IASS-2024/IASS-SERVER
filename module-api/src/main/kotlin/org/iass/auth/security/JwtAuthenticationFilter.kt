package org.iass.auth.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.iass.auth.config.SecurityConfig
import org.iass.auth.jwt.JwtTokenProvider
import org.springframework.lang.NonNull
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class JwtAuthenticationFilter (
	private val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {

	@Throws(IOException::class, ServletException::class, IOException::class)
	override fun doFilterInternal(@NonNull request: HttpServletRequest, @NonNull response: HttpServletResponse, @NonNull filterChain: FilterChain) {
		for (whiteUrl in SecurityConfig.FILTER_WHITE_LIST) {
			if (request.requestURI.contains(whiteUrl)) {
				filterChain.doFilter(request, response)
				return
			}
		}
		val token = getJwtFromRequest(request)
		jwtTokenProvider.validateToken(token)

		val userId = jwtTokenProvider.getUserFromJwt(token)
		val authentication = UserAuthentication(userId, null, null)
		authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
		SecurityContextHolder.getContext().authentication = authentication
		filterChain.doFilter(request, response)
	}

	private fun getJwtFromRequest(request: HttpServletRequest): String? {
		val bearerToken = request.getHeader("Authorization")
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length)
		}
		return null
	}

}
