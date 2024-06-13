package org.iass.user.facade

import org.iass.dto.response.ApiResponse
import org.iass.user.dto.LoginRequest
import org.iass.user.dto.LoginResponse
import org.springframework.http.ResponseEntity
import java.net.URI

interface UserFacade{
	fun login(authorization: String, request: LoginRequest): LoginResponse
}
