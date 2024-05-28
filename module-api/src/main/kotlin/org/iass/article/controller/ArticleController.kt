package org.iass.article.controller

import org.iass.article.facade.ArticleFacade
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/article")
class ArticleController(
	private val articleFacade: ArticleFacade
)
