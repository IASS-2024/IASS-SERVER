tasks.getByName("bootJar") {
	enabled = true
}

tasks.getByName("jar") {
	enabled = false
}

dependencies {
	implementation(project(":module-common"))
	implementation(project(":module-domain"))
	implementation(project(":module-external"))

	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")

	// DB
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	runtimeOnly("com.mysql:mysql-connector-j")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis:3.2.1")

	//JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	//Logging
	implementation ("io.github.oshai:kotlin-logging-jvm:6.0.9")

	//Security
	implementation ("org.springframework.boot:spring-boot-starter-security")

}
