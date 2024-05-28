package org.iass.repository.mongo.generation

import org.iass.model.generation.Generation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GenerationRepository : MongoRepository<Generation, Long>
