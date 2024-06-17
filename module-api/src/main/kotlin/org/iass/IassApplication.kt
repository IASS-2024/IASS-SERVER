package org.iass

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication(scanBasePackageClasses = [CommonRoot::class, DomainRoot::class, ExternalRoot::class])
class IassApplication

fun main(args: Array<String>) {
	runApplication<IassApplication>(*args)
}
