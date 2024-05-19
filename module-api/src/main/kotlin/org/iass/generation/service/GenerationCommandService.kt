package org.iass.generation.service

import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.model.generation.Generation
import org.iass.repository.generation.GenerationMongoRepository
import org.iass.util.RandomStringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GenerationCommandService(
	private val generationMongoRepository: GenerationMongoRepository
) {
	fun create(request: GenerationCreateRequest): Generation {
		Generation(
			order = request.order,
			startAt = request.startAt,
			period = request.period,
			articlePenalty = request.articlePenalty,
			feedbackPenalty = request.feedbackPenalty,
			ticketCount = request.ticketCount,
			inviteCode = RandomStringUtils.generate()
		).let {
			return generationMongoRepository.save(it)
		}
	}
}