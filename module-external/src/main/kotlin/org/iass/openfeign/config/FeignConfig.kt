package org.iass.openfeign.config

import org.iass.ExternalRoot
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackageClasses = [ExternalRoot::class])
class FeignConfig
