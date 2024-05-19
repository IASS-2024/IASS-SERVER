package org.iass.generation.controller

import jakarta.validation.Valid
import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.generation.facade.GenerationFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/generation")
class GenerationController(
	private val generationFacade: GenerationFacade
) {
	@PostMapping
	fun create(
		@Valid @RequestBody request: GenerationCreateRequest
	): ResponseEntity<URI> {
		return ResponseEntity.created(generationFacade.create(request)).build()
	}
}
