package org.iass.generation.dto.request

import jakarta.validation.constraints.Positive
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class GenerationCreateRequest(
	@field:Positive
	val order: Int,
	@field:DateTimeFormat(pattern = "yyyy-MM-dd")
	val startAt: LocalDate,
	@field:Positive
	val period: Int,
	@field:Positive
	val articlePenalty: Int,
	@field:Positive
	val feedbackPenalty: Int,
	@field:Positive
	val ticketCount: Int
)
