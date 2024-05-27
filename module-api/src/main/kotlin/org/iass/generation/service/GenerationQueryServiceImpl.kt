package org.iass.generation.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GenerationQueryServiceImpl() : GenerationQueryService
