package org.iass.article.service

import org.iass.article.dto.request.ArticleCreateRequest
import org.iass.exception.NotFoundException
import org.iass.model.article.Article
import org.iass.repository.mongo.article.ArticleRepository
import org.iass.repository.mongo.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleCommandServiceImpl(
	private val userRepository: UserRepository,
	private val articleRepository: ArticleRepository
) : ArticleCommandService {
	override fun create(userId: String, request: ArticleCreateRequest): String {
		val user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException()
		return articleRepository.save(
			Article(
				request.url,
				request.description,
				request.keyword,
				user,
				0
			)
		).id!!
	}
}
