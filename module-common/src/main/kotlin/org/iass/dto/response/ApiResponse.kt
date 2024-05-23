package org.iass.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("status", "code", "message", "data")
data class ApiResponse<T>
	@JvmOverloads
	constructor(
		val status: Int,
		val code: Int,
		val message: String,
		@JsonInclude(JsonInclude.Include.NON_NULL)
		val data: T? = null
	) {
		companion object {
			@JvmStatic
			fun success(successType: SuccessType): ApiResponse<Nothing> {
				return ApiResponse(successType.status.value(), successType.code, successType.message)
			}

			@JvmStatic
			fun <T> success(
				successType: SuccessType,
				data: T
			): ApiResponse<T> {
				return ApiResponse(successType.status.value(), successType.code, successType.message, data)
			}

			@JvmStatic
			fun error(errorType: ErrorType): ApiResponse<Nothing> {
				return ApiResponse(errorType.status.value(), errorType.code, errorType.message)
			}

			@JvmStatic
			fun <T> error(
				errorType: ErrorType,
				data: T
			): ApiResponse<T> {
				return ApiResponse(errorType.status.value(), errorType.code, errorType.message, data)
			}

			@JvmStatic
			fun error(
				errorType: ErrorType,
				message: String
			): ApiResponse<Nothing> {
				return ApiResponse(errorType.status.value(), errorType.code, message)
			}

			@JvmStatic
			fun <T> error(
				errorType: ErrorType,
				message: String,
				data: T
			): ApiResponse<T> {
				return ApiResponse(errorType.status.value(), errorType.code, message, data)
			}

			@JvmStatic
			fun error(
				errorType: ErrorType,
				e: Exception
			): ApiResponse<Exception> {
				return ApiResponse(errorType.status.value(), errorType.code, errorType.message, e)
			}
		}
	}
