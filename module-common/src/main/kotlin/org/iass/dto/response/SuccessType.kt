package org.iass.dto.response

import org.springframework.http.HttpStatus

enum class SuccessType(val status: HttpStatus, val code: Int, val message: String) {
	/**
	 * 200 OK (2000 ~ 2099)
	 */
	OK(HttpStatus.OK, 2000, "성공하였습니다"),
	SUCCESS_SIGNIN(HttpStatus.OK, 2001, "회원 가입에 성공하였습니다."),

	/**
	 * 201 CREATED (2100 ~ 2199)
	 */
	CREATED(HttpStatus.CREATED, 2100, "자원 생성을 성공하였습니다"),
	SUCCESS_APPLE_LOGIN(HttpStatus.CREATED, 2101, "애플 로그인을 성공하였습니다"),
	SUCCESS_CREATE_GENERATION(HttpStatus.CREATED, 2102, "기수 생성을 성공하였습니다."),
	SUCCESS_CREATE_ARTICLE(HttpStatus.CREATED, 2103, "아티클 생성을 성공하였습니다."),


	/**
	 * 204 NO CONTENT (2400 ~ 2499)
	 */
	NO_CONTENT(HttpStatus.NO_CONTENT, 2400, "성공하였습니다")
}
