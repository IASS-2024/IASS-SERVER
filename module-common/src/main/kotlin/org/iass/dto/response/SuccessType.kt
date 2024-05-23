package org.iass.dto.response

import org.springframework.http.HttpStatus

enum class SuccessType(val status: HttpStatus, val code: Int, val message: String) {
	/**
	 * 200 OK (2000 ~ 2099)
	 */
	OK(HttpStatus.OK, 2000, "성공하였습니다"),

	/**
	 * 201 CREATED (2100 ~ 2199)
	 */
	CREATED(HttpStatus.CREATED, 2100, "자원 생성을 성공하였습니다"),

	/**
	 * 204 NO CONTENT (2400 ~ 2499)
	 */
	NO_CONTENT(HttpStatus.NO_CONTENT, 2400, "성공하였습니다")
}
