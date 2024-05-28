package org.iass.article.facade

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleFacadeImpl() : ArticleFacade
