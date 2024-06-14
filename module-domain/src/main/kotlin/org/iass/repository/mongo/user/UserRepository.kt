package org.iass.repository.mongo.user

import org.iass.model.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
	fun findUserBySocialId(socialId: String): User?
}
