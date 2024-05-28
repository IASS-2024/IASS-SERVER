package org.iass.article.facade

import org.iass.article.dto.request.ArticleCreateRequest

interface ArticleFacade {
	fun create(request: ArticleCreateRequest): String
}
