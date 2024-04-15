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

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
}