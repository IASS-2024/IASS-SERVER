package org.iass.auth.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component

class JwtExceptionFilter(
	val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
	override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
		response.characterEncoding = "utf-8"
		try {
			filterChain.doFilter(request, response)
		} catch (e: CommonException) {
			if (e.errorType == ErrorType.UNKNOWN_TOKEN) {
				setErrorResponse(response, ErrorType.UNKNOWN_TOKEN)
			} else if (e.errorType == ErrorType.WRONG_TYPE_TOKEN) {
				setErrorResponse(response, ErrorType.WRONG_TYPE_TOKEN)
			} else if (e.errorType == ErrorType.EXPIRED_TOKEN) {
				setErrorResponse(response, ErrorType.EXPIRED_TOKEN)
			} else if (e.errorType == ErrorType.UNSUPPORTED_TOKEN) {
				setErrorResponse(response, ErrorType.UNSUPPORTED_TOKEN)
			} else if (e.errorType == ErrorType.WRONG_SIGNATURE_TOKEN) {
				setErrorResponse(response, ErrorType.WRONG_SIGNATURE_TOKEN)
			}
		}
	}

	fun setErrorResponse(response: HttpServletResponse, errorType: ErrorType) {
		response.status = errorType.status.value()
		response.contentType = MediaType.APPLICATION_JSON_VALUE
		val errorResponse = ErrorResponse(errorType.status.value(), errorType.code, errorType.message)
		try {
			response.writer.write(objectMapper.writeValueAsString(errorResponse))
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	data class ErrorResponse(
		val status: Int,
		val code: Int,
		val message: String
	)
}
