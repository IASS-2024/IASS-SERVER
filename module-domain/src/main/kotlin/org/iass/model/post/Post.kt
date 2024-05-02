package org.iass.model.post

import jakarta.persistence.Id
import org.iass.model.common.BaseTimeEntity
import org.iass.model.user.User
import org.springframework.data.mongodb.core.mapping.Document

@Document("post")
class Post(
	title: String,
	content: String,
	val type: PostType,
	val user: User
) : BaseTimeEntity() {
	@Id
	var id: String? = null

	var title = title
		protected set

	var content = content
		protected set
}
