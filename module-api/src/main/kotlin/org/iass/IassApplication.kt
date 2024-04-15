package org.iass

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
	scanBasePackageClasses = [ CommonRoot::class, DomainRoot::class, ExternalRoot::class ]
)
class IassApplication

fun main(args: Array<String>) {
	runApplication<IassApplication>(*args)
}
