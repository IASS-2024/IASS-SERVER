package org.iass

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IassApplication

fun main(args: Array<String>) {
	runApplication<IassApplication>(*args)
}
