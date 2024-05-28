package org.iass.article.service

import org.iass.article.dto.request.ArticleCreateRequest

interface ArticleCommandService {
	fun create(request: ArticleCreateRequest): String
}
