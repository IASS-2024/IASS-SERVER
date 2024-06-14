package org.iass.article.controller

import jakarta.validation.Valid
import org.iass.article.dto.request.ArticleCreateRequest
import org.iass.article.facade.ArticleFacade
import org.iass.auth.jwt.JwtTokenProvider
import org.iass.dto.response.ApiResponse
import org.iass.dto.response.SuccessType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.security.Principal

private const val ARTICLE_URI = "article"

@RestController
@RequestMapping("/article")
class ArticleController(
	private val articleFacade: ArticleFacade,
	private val jwtTokenProvider: JwtTokenProvider
) {
	@PostMapping
	fun create(
		principal: Principal,
		@Valid @RequestBody request: ArticleCreateRequest
	): ResponseEntity<ApiResponse<Nothing>> {
		articleFacade.create(jwtTokenProvider.getUserIdFromPrincipal(principal), request)
		return ResponseEntity.ok(ApiResponse.success(SuccessType.SUCCESS_CREATE_ARTICLE))
	}
}
