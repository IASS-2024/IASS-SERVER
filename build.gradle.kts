import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.getByName("bootJar") {
	enabled = false
}

tasks.getByName("jar") {
	enabled = true
}

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
}

allprojects {
	group = "org.iass"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.kapt")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
	apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	allOpen {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.Embeddable")
		annotation("jakarta.persistence.MappedSuperclass")
	}

	extra["sentryVersion"] = "7.3.0"

	dependencyManagement {
		imports {
			mavenBom("io.sentry:sentry-bom:${property("sentryVersion")}")
		}
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-actuator")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("io.sentry:sentry-spring-boot-starter-jakarta")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
