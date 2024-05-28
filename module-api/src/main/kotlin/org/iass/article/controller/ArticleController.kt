package org.iass.article.controller

import jakarta.validation.Valid
import org.iass.article.dto.request.ArticleCreateRequest
import org.iass.article.facade.ArticleFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

private const val ARTICLE_URI = "article"

@RestController
@RequestMapping("/article")
class ArticleController(
	private val articleFacade: ArticleFacade
) {
	@PostMapping
	fun create(
		@Valid @RequestBody request: ArticleCreateRequest
	): ResponseEntity<URI> {
		return ResponseEntity.created(URI.create(ARTICLE_URI + "/" + articleFacade.create(request))).build()
	}
}
