package org.iass.generation.facade

import org.iass.generation.dto.request.GenerationCreateRequest
import java.net.URI

interface GenerationFacade {
	fun create(request: GenerationCreateRequest): URI
}
