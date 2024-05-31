package org.iass.repository.mongo.article

import org.iass.model.article.Article
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : MongoRepository<Article, String>
