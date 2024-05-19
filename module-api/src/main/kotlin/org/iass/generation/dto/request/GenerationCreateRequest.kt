package org.iass.generation.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class GenerationCreateRequest(
	@field:NotNull
	@field:Positive
	val order: Int,
	@field:NotNull
	@field:DateTimeFormat(pattern = "yyyy-MM-dd")
	val startAt: LocalDate,
	@field:NotNull
	@field:Positive
	val period: Int,
	@field:NotNull
	@field:Positive
	val articlePenalty: Int,
	@field:NotNull
	@field:Positive
	val feedbackPenalty: Int,
	@field:NotNull
	@field:Positive
	val ticketCount: Int
)
