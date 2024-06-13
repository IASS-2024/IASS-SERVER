package org.iass.model.user

import jakarta.persistence.Id
import org.iass.model.generation.Generation
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
class User(
	nickname: String,
	val socialId: String,
	imgUrl: String,
	spareTicket: Int,
	deposit: Int,
	val socialType: SocialType,
	val generation: Generation?
) {
	@Id
	var id: String? = null

	var nickname = nickname
		private set

	var imgUrl = imgUrl
		private set

	var spareTicket = spareTicket
		private set

	var deposit = deposit
		private set

	constructor(socialId: String, socialType: SocialType) : this(
		nickname = "",
		socialId = socialId,
		imgUrl = "",
		spareTicket = 0,
		deposit = 0,
		socialType = socialType,
		generation = null
	)

	fun useTicket() = --this.spareTicket

	fun getPenalty(amount: Int) {
		this.deposit -= amount
	}
}
