package org.iass.article.dto.request

import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.URL

data class ArticleCreateRequest(
	@field:URL
	val url: String,
	@field:NotNull
	val description: String,
	@field:NotNull
	val keyword: String
)
