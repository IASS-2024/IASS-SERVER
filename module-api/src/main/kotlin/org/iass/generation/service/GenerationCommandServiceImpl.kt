package org.iass.generation.service

import org.iass.dto.response.ErrorType
import org.iass.exception.NotFoundException
import org.iass.generation.dto.request.GenerationCreateRequest
import org.iass.model.generation.Generation
import org.iass.repository.mongo.generation.GenerationRepository
import org.iass.repository.mongo.user.UserRepository
import org.iass.util.RandomStringUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GenerationCommandServiceImpl(
	private val userRepository: UserRepository,
	private val generationRepository: GenerationRepository
) : GenerationCommandService {
	override fun create(
		userId: String,
		request: GenerationCreateRequest
	): Generation {
		// TODO - admin만 가능하도록 변경 필요
		userRepository.findByIdOrNull(userId) ?: throw NotFoundException(ErrorType.NOT_FOUND_USER)
		Generation(
			order = request.order,
			startAt = request.startAt,
			period = request.period,
			deposit = request.deposit,
			articlePenalty = request.articlePenalty,
			feedbackPenalty = request.feedbackPenalty,
			ticketCount = request.ticketCount,
			inviteCode = RandomStringUtils.generate()
		).let {
			return generationRepository.save(it)
		}
	}
}
