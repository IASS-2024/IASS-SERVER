package org.iass.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
@EnableMongoRepositories(basePackages = "org.iass")
public class MongoDBConfig {
}
