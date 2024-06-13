tasks.getByName("bootJar") {
	enabled = false
}

tasks.getByName("jar") {
	enabled = true
}

dependencies {
	implementation(project(":module-common"))

	// openfeign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.0")

	//JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

}
