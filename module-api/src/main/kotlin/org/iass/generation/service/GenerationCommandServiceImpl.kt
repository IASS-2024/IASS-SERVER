package org.iass.generation.service

import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.model.generation.Generation
import org.iass.repository.mongo.generation.GenerationRepository
import org.iass.util.RandomStringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GenerationCommandServiceImpl(
	private val generationRepository: GenerationRepository
) : GenerationCommandService {
	override fun create(request: GenerationCreateRequest): Generation {
		Generation(
			order = request.order,
			startAt = request.startAt,
			period = request.period,
			articlePenalty = request.articlePenalty,
			feedbackPenalty = request.feedbackPenalty,
			ticketCount = request.ticketCount,
			inviteCode = RandomStringUtils.generate()
		).let {
			return generationRepository.save(it)
		}
	}
}
