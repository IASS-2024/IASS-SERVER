tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    implementation(project(":module-common"))

    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    runtimeOnly("com.mysql:mysql-connector-j")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
}