package org.iass.dto.response

import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: Int, val message: String) {
	/**
	 * 400 BAD REQUEST (4000 ~ 4099)
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, 4000, "잘못된 요청입니다"),

	/**
	 * 401 UNAUTHORIZED (4100 ~ 4199)
	 */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4100, "인증되지 않았습니다"),

	/**
	 * 403 FORBIDDEN (4300 ~ 4399)
	 */
	FORBIDDEN(HttpStatus.FORBIDDEN, 4300, "해당 자원에 접근 권한이 없습니다"),

	/**
	 * 404 NOT FOUND (4400 ~ 4499)
	 */
	NOT_FOUND(HttpStatus.NOT_FOUND, 4400, "존재하지 않는 리소스입니다"),

	/**
	 * 405 METHOD NOT ALLOWED (4500 ~ 4599)
	 */
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 4500, "잘못된 HTTP Method 요청입니다"),

	/**
	 * 500 INTERNAL SERVER (5000 ~ 5099)
	 */
	INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "알 수 없는 서버 에러입니다")
}
