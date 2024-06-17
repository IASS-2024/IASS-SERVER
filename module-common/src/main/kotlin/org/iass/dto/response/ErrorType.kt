package org.iass.dto.response

import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: Int, val message: String) {
	/**
	 * 400 BAD REQUEST (4000 ~ 4099)
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, 4000, "잘못된 요청입니다"),
	REQUEST_VALIDATION(HttpStatus.BAD_REQUEST, 4001, "잘못된 요청입니다"),
	INVALID_TYPE(HttpStatus.BAD_REQUEST, 4002, "잘못된 타입이 입력되었습니다"),
	INVALID_MISSING_HEADER(HttpStatus.BAD_REQUEST, 4003, "요청에 필요한 헤더값이 존재하지 않습니다"),
	INVALID_HTTP_REQUEST(HttpStatus.BAD_REQUEST, 4004, "허용되지 않는 문자열이 입력되었습니다"),

	// Apple 관련
	INVALID_ENCRYPT_COMMUNICATION(HttpStatus.BAD_REQUEST, 4005, "Apple OAuth 통신 암호화 중 문제가 발생했습니다."),
	INVALID_APPLE_PUBLIC_KEY(HttpStatus.BAD_REQUEST, 4006, "Apple JWT 값의 alg, kid 정보가 올바르지 않습니다."),
	INVALID_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, 4007, "Apple OAuth Identity Token 형식이 올바르지 않습니다."),
	EXPIRED_APPLE_IDENTITY_TOKEN(HttpStatus.BAD_REQUEST, 4008, "Apple OAuth 로그인 중 Identity Token 유효기간이 만료됐습니다."),
	INVALID_APPLE_CLAIMS(HttpStatus.BAD_REQUEST, 4009, "Apple OAuth Claims 값이 올바르지 않습니다."),
	CREATE_PUBLIC_KEY_EXCEPTION(HttpStatus.BAD_REQUEST, 4010, "Apple OAuth 로그인 중 public verify 생성에 문제가 발생했습니다."),

	/**
	 * 401 UNAUTHORIZED (4100 ~ 4199)
	 */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4100, "인증되지 않았습니다"),
	UNKNOWN_TOKEN(HttpStatus.UNAUTHORIZED, 4101, "인증 토큰이 존재하지 않습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, 4102, "지원하지 않는 토큰 방식입니다."),
    INVALID_AUTH(HttpStatus.UNAUTHORIZED, 4103, "인증되지 않은 사용자입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 4104, "만료된 Token입니다."),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, 4105, "잘못된 형식의 Token입니다"),
    WRONG_SIGNATURE_TOKEN(HttpStatus.UNAUTHORIZED, 4106, "Signature가 잘못된 Token입니다."),

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
