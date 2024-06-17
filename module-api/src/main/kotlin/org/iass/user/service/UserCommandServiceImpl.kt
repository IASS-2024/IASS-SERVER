package org.iass.user.service

import org.iass.auth.jwt.JwtTokenProvider
import org.iass.auth.jwt.TokenResponse
import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException
import org.iass.exception.NotFoundException
import org.iass.model.user.SocialType
import org.iass.model.user.User
import org.iass.repository.mongo.generation.GenerationRepository
import org.iass.repository.mongo.user.UserRepository
import org.iass.user.dto.request.LoginRequest
import org.iass.user.dto.response.LoginResponse
import org.iass.user.dto.request.SignInRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class UserCommandServiceImpl(
	private val userRepository: UserRepository,
	private val generationRepository: GenerationRepository,
	private val jwtTokenProvider: JwtTokenProvider,
	private val appleLoginService: AppleLoginService
) : UserCommandService {
	override fun login(authorization: String, request: LoginRequest) : LoginResponse {
		val socialId = appleLoginService.getAppleId(authorization)
		val findUser = userRepository.findUserBySocialId(socialId)
		val user = findUser?.apply {
			resetSocialType(request.socialType)
		} ?: User(
			socialId = socialId,
			socialType = request.socialType
		).also {
			userRepository.save(it)
		}
		val token = user.id?.let {
			TokenResponse(
				jwtTokenProvider.generateAccessToken(it),
				jwtTokenProvider.generateRefreshToken(it)
			) } ?: throw NotFoundException(ErrorType.NOT_FOUND_USER)
		return LoginResponse(user.id, token)
	}

	override fun signIn(userId: String, request: SignInRequest) {
		val user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(ErrorType.NOT_FOUND_USER)
		val generation = generationRepository.findGenerationByInviteCode(request.code) ?: throw NotFoundException(ErrorType.NOT_FOUND_GENERATION)
		user.signIn(request.nickname,
					request.description,
					generation.ticketCount,
					generation.deposit,
					generation)
		userRepository.save(user)
	}

	override fun reissue(authorization: String): TokenResponse {
		val token = authorization.substring("Bearer ".length)
		val userId = jwtTokenProvider.validateRefreshToken(token)
		jwtTokenProvider.deleteRefreshToken(userId)
		return jwtTokenProvider.reissuedToken(userId)
	}

	override fun logout(userId: String) {
		userRepository.findByIdOrNull(userId) ?: throw NotFoundException(ErrorType.NOT_FOUND_USER)
		jwtTokenProvider.deleteRefreshToken(userId)
	}

	override fun withdrawal(userId: String) {
		val user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(ErrorType.NOT_FOUND_USER)
		jwtTokenProvider.deleteRefreshToken(userId)
		if (user.socialType != SocialType.WITHDRAWAL) {
			user.withdrawal()
			userRepository.save(user)
		} else {
			throw CommonException(ErrorType.BAD_REQUEST)
		}

	}

}

