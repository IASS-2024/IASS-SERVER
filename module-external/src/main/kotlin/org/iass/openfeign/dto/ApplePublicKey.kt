package org.iass.openfeign.dto

data class ApplePublicKey(
	val kty: String,
	val kid: String,
	val use: String,
	val alg: String,
	val n: String,
	val e: String
)
