package org.iass.generation.service

import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.model.generation.Generation

interface GenerationCommandService {
	fun create(
		userId: String,
		request: GenerationCreateRequest
	): Generation
}
