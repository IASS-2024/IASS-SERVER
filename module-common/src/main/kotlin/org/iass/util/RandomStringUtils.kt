package org.iass.util

import java.util.concurrent.ThreadLocalRandom

class RandomStringUtils {
	companion object {
		val random = ThreadLocalRandom.current()

		fun generate(): String {
			val key = StringBuilder()
			for (i in 0..5) {
				when (random.nextInt(3)) {
					0 -> key.append((random.nextInt(26) + 97).toChar())
					1 -> key.append((random.nextInt(26) + 65).toChar())
					2 -> key.append(random.nextInt(10))
				}
			}
			return key.toString()
		}
	}
}
