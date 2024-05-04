package org.iass.model.article

import jakarta.persistence.Id
import org.iass.model.common.BaseTimeEntity
import org.iass.model.user.User
import org.springframework.data.mongodb.core.mapping.Document

@Document("article")
class Article(
	url: String,
	description: String,
	keyword: String,
	val user: User,
	val week: Int
) : BaseTimeEntity() {
	@Id
	var id: String? = null

	var url = url
		protected set

	var description = description
		protected set

	var keyword = keyword
		protected set

	fun modifyKeyword(keyword: String) {
		this.keyword = keyword
	}
}
