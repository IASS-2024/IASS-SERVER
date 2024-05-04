package org.iass.model.mate

import jakarta.persistence.Id
import org.iass.model.common.BaseTimeEntity
import org.iass.model.user.User
import org.springframework.data.mongodb.core.mapping.Document

@Document("mate")
class Mate(
	val week: String,
	val reviewee: User,
	val reviewer: User
) : BaseTimeEntity() {
	@Id
	var id: String? = null
}
