package org.iass.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintDefinitionException
import jakarta.validation.UnexpectedTypeException
import org.iass.dto.response.ApiResponse
import org.iass.dto.response.ErrorType
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.io.IOException

private val logger = KotlinLogging.logger { }

@RestControllerAdvice
class ExceptionControllerAdvice {
	/**
	 * 400 BAD REQUEST
	 */
	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<*>> {
		val errors = e.bindingResult
		val validateDetails =
			errors.fieldErrors.associate {
				"valid_${it.field}" to it.defaultMessage
			}
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.REQUEST_VALIDATION, validateDetails), e.statusCode)
	}

	@ExceptionHandler(UnexpectedTypeException::class)
	fun handleUnexpectedTypeException(e: UnexpectedTypeException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INVALID_TYPE), HttpStatus.BAD_REQUEST)
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException::class)
	fun handleMethodArgumentTypeMismatchException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INVALID_TYPE), HttpStatus.BAD_REQUEST)
	}

	@ExceptionHandler(MissingRequestHeaderException::class)
	fun handleMissingRequestHeaderException(e: MissingRequestHeaderException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INVALID_MISSING_HEADER), e.statusCode)
	}

	@ExceptionHandler(HttpMessageNotReadableException::class)
	fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INVALID_HTTP_REQUEST), HttpStatus.BAD_REQUEST)
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException::class)
	fun handleHttpRequestMethodNotSupportedException(
		e: HttpRequestMethodNotSupportedException
	): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.METHOD_NOT_ALLOWED), e.statusCode)
	}

	@ExceptionHandler(ConstraintDefinitionException::class)
	fun handleConstraintDefinitionException(e: ConstraintDefinitionException): ResponseEntity<ApiResponse<*>> {
		return ResponseEntity(ApiResponse.error(ErrorType.INVALID_HTTP_REQUEST, e.toString()), HttpStatus.BAD_REQUEST)
	}

	@ExceptionHandler(BadRequestException::class)
	fun handleBadRequestException(e: BadRequestException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(e.errorType), e.getHttpStatus())
	}

	/**
	 * 401 UNAUTHORIZED
	 */
	@ExceptionHandler(UnauthorizedException::class)
	fun handleUnauthorizedException(e: UnauthorizedException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(e.errorType), e.getHttpStatus())
	}

	/**
	 * 403 FORBIDDEN
	 */
	@ExceptionHandler(ForbiddenException::class)
	fun handleForbiddenException(e: ForbiddenException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(e.errorType), e.getHttpStatus())
	}

	/**
	 * 500 INTERNAL SERVER
	 */
	@ExceptionHandler(Exception::class)
	fun handleException(
		e: Exception,
		request: HttpServletRequest
	): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@ExceptionHandler(IllegalArgumentException::class)
	fun handleIllegalArgumentException(
		e: IllegalArgumentException,
		request: HttpServletRequest
	): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@ExceptionHandler(IOException::class)
	fun handleIOException(
		e: IOException,
		request: HttpServletRequest
	): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@ExceptionHandler(RuntimeException::class)
	fun handleRuntimeException(
		e: RuntimeException,
		request: HttpServletRequest
	): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@ExceptionHandler(InternalServerException::class)
	fun handleInternalServerException(e: InternalServerException): ResponseEntity<ApiResponse<*>> {
		logger.error(e) { e.message }
		return ResponseEntity(ApiResponse.error(e.errorType), e.getHttpStatus())
	}
}
