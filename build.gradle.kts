import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0-Beta4"
    kotlin("plugin.spring") version "2.0.0-Beta4"
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.ben-manes.versions") version "0.51.0"
}

group = "hu.szmozes"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

spotless {
    format("misc") {
        target(".gitignore", "*.yml", "*.md", "*.Dockerfile")

        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlin {
        ktlint()
    }
    kotlinGradle {
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
