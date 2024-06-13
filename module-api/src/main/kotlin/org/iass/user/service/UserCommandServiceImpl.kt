package org.iass.user.service

import org.iass.auth.jwt.JwtTokenProvider
import org.iass.auth.jwt.TokenResponse
import org.iass.auth.security.UserAuthentication
import org.iass.dto.response.ApiResponse
import org.iass.dto.response.SuccessType
import org.iass.model.user.SocialType
import org.iass.model.user.User
import org.iass.repository.mongo.user.UserRepository
import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class UserCommandServiceImpl(
	private val userRepository: UserRepository,
	private val jwtTokenProvider: JwtTokenProvider,
	private val appleLoginService: AppleLoginService
) : UserCommandService {
	override fun login(authorization: String, request: LoginRequest) : LoginResponse {
		val socialId: String = appleLoginService.getAppleId(authorization)
		val findUser: User? = userRepository.findUserBySocialId(socialId)

		val user = findUser ?: User(
					socialId = socialId,
					socialType = request.socialType,
				).let { userRepository.save(it) }
		val userAuthentication = UserAuthentication(user.id, null, null)
		val token = TokenResponse(jwtTokenProvider.generateAccessToken(userAuthentication), jwtTokenProvider.generateRefreshToken(userAuthentication))
		return LoginResponse.of(user.id, token)
	}

}

