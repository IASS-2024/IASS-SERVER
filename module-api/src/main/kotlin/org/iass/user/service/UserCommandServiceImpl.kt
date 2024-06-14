package org.iass.user.service

import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.iass.auth.jwt.JwtTokenProvider
import org.iass.auth.jwt.TokenResponse
import org.iass.auth.security.UserAuthentication
import org.iass.dto.response.ErrorType
import org.iass.exception.CommonException
import org.iass.model.user.SocialType
import org.iass.model.user.User
import org.iass.repository.mongo.generation.GenerationRepository
import org.iass.repository.mongo.user.UserRepository
import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.iass.user.dto.SignInRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


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
		)
		userRepository.save(user)
		val token = TokenResponse(jwtTokenProvider.generateAccessToken(user.id), jwtTokenProvider.generateRefreshToken(user.id))
		return LoginResponse.of(user.id, token)
	}

	override fun signIn(userId: String, request: SignInRequest) {
		val user = userRepository.findByIdOrNull(userId) ?: throw CommonException(ErrorType.NOT_FOUND)
		val generation = generationRepository.findGenerationByInviteCode(request.code) ?: throw CommonException(ErrorType.NOT_FOUND) // TODO - 예외 발생
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
		userRepository.findByIdOrNull(userId) ?: throw CommonException(ErrorType.NOT_FOUND)
		jwtTokenProvider.deleteRefreshToken(userId)
	}

	override fun withdrawal(userId: String) {
		val user = userRepository.findByIdOrNull(userId) ?: throw CommonException(ErrorType.NOT_FOUND)
		jwtTokenProvider.deleteRefreshToken(userId)
		if (user.socialType != SocialType.WITHDRAWAL) {
			user.withdrawal()
			userRepository.save(user)
		} else {
			throw CommonException(ErrorType.BAD_REQUEST)
		}

	}

}

