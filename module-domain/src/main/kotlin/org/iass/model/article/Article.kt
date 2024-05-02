package org.iass.model.article

import jakarta.persistence.Id
import org.iass.model.user.User
import org.springframework.data.mongodb.core.mapping.Document

@Document("article")
class Article(
	url: String,
	description: String,
	keyword: String,
	val user: User,
	val week: Int
) {
	@Id
	var id: String? = null

	var url = url
		private set

	var description = description
		private set

	var keyword = keyword
		private set

	fun modifyKeyword(keyword: String) {
		this.keyword = keyword
	}
}
