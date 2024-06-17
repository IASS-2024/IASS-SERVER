package org.iass.auth.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisConfig (
	@Value("\${redis.host}")
	private val host: String,

	@Value("\${redis.port}")
	private val port: Int
) {
	@Bean
	fun redisConnectionFactory(): RedisConnectionFactory {
		return LettuceConnectionFactory(host, port)
	}

	@Bean
	@Primary
	fun redisTemplate(): RedisTemplate<String, String> {
		val redisTemplate: RedisTemplate<String, String> = RedisTemplate()
		redisTemplate.setKeySerializer(StringRedisSerializer())
		redisTemplate.setValueSerializer(StringRedisSerializer())
		redisTemplate.setConnectionFactory(redisConnectionFactory())
		return redisTemplate
	}
}
