package org.iass.article.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleCommandServiceImpl() : ArticleCommandService
