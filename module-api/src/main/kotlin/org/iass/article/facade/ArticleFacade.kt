package org.iass.article.facade

import org.iass.article.dto.request.ArticleCreateRequest
import org.iass.article.service.ArticleCommandService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleFacade(
	private val articleCommandService: ArticleCommandService
) {
	fun create(userId: String, request: ArticleCreateRequest): String {
		return articleCommandService.create(userId, request)
	}
}
