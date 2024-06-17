package org.iass.auth.config

import org.iass.auth.filter.IASSExceptionHandler
import org.iass.auth.security.JwtAuthenticationFilter
import org.iass.auth.security.JwtExceptionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig (
	private val jwtAuthenticationFilter: JwtAuthenticationFilter,
	private val iassExceptionHandler: IASSExceptionHandler,
	private val jwtExceptionFilter: JwtExceptionFilter
){
	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()

			.sessionManagement { sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
			.exceptionHandling { exceptionHandlingConfigurer -> exceptionHandlingConfigurer.authenticationEntryPoint(iassExceptionHandler) }
			.exceptionHandling { exceptionHandlingConfigurer -> exceptionHandlingConfigurer.accessDeniedHandler(iassExceptionHandler) }

			.authorizeHttpRequests { authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers(*SECURITY_WHITE_LIST.toTypedArray()).permitAll() }
			.authorizeHttpRequests { authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().authenticated() }
			.authorizeHttpRequests { authorizationManagerRequestMatcherRegistry -> http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java) }
			.authorizeHttpRequests { authorizationManagerRequestMatcherRegistry -> http.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter::class.java) }

		return http.build()
	}

	@Bean
	fun corsConfigurationSource(): CorsConfigurationSource {
		val config = CorsConfiguration()
		config.allowCredentials = true
		config.addAllowedOrigin("http://localhost:8080")
		config.addAllowedOrigin("https://appleid.apple.com/auth")
		config.addAllowedHeader("*")
		config.addAllowedMethod("*")
		config.addExposedHeader("Authorization")

		val source = UrlBasedCorsConfigurationSource()
		source.registerCorsConfiguration("/**", config)
		return source
	}

	companion object WhiteListConstants {
		val FILTER_WHITE_LIST = listOf(
			"/auth/keys",
			"/auth",
			"/user/login",
			"/login",
			"/actuator/health",
			"/error"
		)
		val SECURITY_WHITE_LIST = listOf(
			"/login/**",
			"/",
			"/actuator/health",
			"/user/**",
			"/error",
			"/webjars/**"
		)
	}
}

