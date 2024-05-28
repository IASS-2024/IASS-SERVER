package org.iass.article.facade

import org.iass.article.dto.request.ArticleCreateRequest
import org.iass.article.service.ArticleCommandService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleFacadeImpl(
	private val articleCommandService: ArticleCommandService
) : ArticleFacade {
	override fun create(request: ArticleCreateRequest): String {
		return articleCommandService.create(request)
	}
}
