tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    // Spring AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
}