package org.iass.article.service

import org.iass.article.dto.request.ArticleCreateRequest
import org.iass.model.article.Article
import org.iass.repository.mongo.article.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleCommandServiceImpl(
	private val articleRepository: ArticleRepository
) : ArticleCommandService {
	override fun create(request: ArticleCreateRequest): String {
		return articleRepository.save(
			Article(
				request.url,
				request.description,
				request.keyword,
				"tempUser",
				0
			)
		).id!!
	}
}
