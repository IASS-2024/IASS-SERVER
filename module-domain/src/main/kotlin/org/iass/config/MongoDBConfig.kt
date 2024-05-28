package org.iass.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoAuditing
@EnableAutoConfiguration(excludeName = ["MongoAutoConfiguration"])
@EnableMongoRepositories(basePackages = ["org.iass.repository.mongo"])
class MongoDBConfig
