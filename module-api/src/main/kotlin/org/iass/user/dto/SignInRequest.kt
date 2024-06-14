package org.iass.user.dto

import jakarta.validation.constraints.NotNull

data class SignInRequest (
	//TODO - 정규식, 글자 제한 Validator 추가
	@field:NotNull
	val nickname: String,
	@field:NotNull
	val description: String,
	@field:NotNull
	val code: String
)
