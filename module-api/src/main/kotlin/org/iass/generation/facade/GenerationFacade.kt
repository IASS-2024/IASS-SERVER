package org.iass.generation.facade

import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.generation.service.GenerationCommandService
import org.iass.generation.service.GenerationQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URI

@Service
@Transactional(readOnly = true)
class GenerationFacade(
	private val generationCommandService: GenerationCommandService,
	private val generationQueryService: GenerationQueryService
) {
	fun create(request: GenerationCreateRequest): URI {
		val generation = generationCommandService.create(request)
		return URI.create("/api/generation/${generation.id}")
	}
}
