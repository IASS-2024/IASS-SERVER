package org.iass.auth.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class IASSExceptionHandler : AccessDeniedHandler, AuthenticationEntryPoint {

	override fun commence(request: HttpServletRequest?,
						  response: HttpServletResponse?,
						  authException: AuthenticationException?) {
		log.error("Authentication Exception Occurs!")
	}

	override fun handle(request: HttpServletRequest?,
						response: HttpServletResponse?,
						accessDeniedException: org.springframework.security.access.AccessDeniedException?) {
		log.error("Forbidden Exception Occurs!")
	}

}

