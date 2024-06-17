package org.iass.openfeign.apple.verify

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppleClaimsValidator(
	@param:Value("\${apple.iss}") private val iss: String,
	@param:Value("\${apple.client-id}") private val clientId: String
) {
	fun isValid(claims: Claims): Boolean =
		(
			claims.getIssuer().contains(iss) &&
				claims.getAudience().equals(clientId)
		)
}
