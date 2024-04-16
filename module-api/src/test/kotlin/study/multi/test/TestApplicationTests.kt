package study.multi.test

import org.iass.IassApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [IassApplication::class])
class TestApplicationTests {
	@Test
	fun contextLoads() {
	}
}
