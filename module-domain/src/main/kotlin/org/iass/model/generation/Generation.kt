package org.iass.model.generation

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("generation")
class Generation(
	val order: Int,
	val startAt: LocalDate,
	val period: Int,
	val articlePenalty: Int,
	val feedbackPenalty: Int,
	val ticketCount: Int,
	val inviteCode: String
) {
	@Id
	var id: String? = null
}
