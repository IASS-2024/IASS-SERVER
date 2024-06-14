package org.iass.generation.controller

import jakarta.validation.Valid
import org.iass.auth.jwt.JwtTokenProvider
import org.iass.dto.response.ApiResponse
import org.iass.dto.response.SuccessType
import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.generation.facade.GenerationFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/generation")
class GenerationController(
	private val generationFacade: GenerationFacade,
	private val jwtTokenProvider: JwtTokenProvider
) {
	@PostMapping
	fun create(
		principal: Principal,
		@Valid @RequestBody request: GenerationCreateRequest
	): ResponseEntity<ApiResponse<Nothing>> {
		generationFacade.create(jwtTokenProvider.getUserIdFromPrincipal(principal), request)
		return ResponseEntity.ok(ApiResponse.success(SuccessType.POST_GENERATION_SUCCESS))
	}
}
