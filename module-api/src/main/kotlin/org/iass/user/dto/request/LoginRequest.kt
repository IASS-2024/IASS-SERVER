package org.iass.user.dto.request

import jakarta.validation.constraints.NotNull
import org.iass.model.user.SocialType

data class LoginRequest (
	@field:NotNull
	val socialType: SocialType
)
