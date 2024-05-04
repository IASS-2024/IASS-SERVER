package org.iass.model.ticket

import jakarta.persistence.Id
import org.iass.model.common.BaseTimeEntity
import org.springframework.data.mongodb.core.mapping.Document

@Document("ticket")
class Ticket(
	val userId: String,
	val week: Int,
	val applyType: ApplyType
) : BaseTimeEntity() {
	@Id
	var id: String? = null
}
