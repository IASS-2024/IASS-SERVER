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
}
