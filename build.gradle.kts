import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.kotlin.dsl.withType

plugins {
    val kotlinVersion = "2.0.20"

    kotlin("jvm") version kotlinVersion
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.ben-manes.versions") version "0.51.0"
}

group = "hu.szmozes"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
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

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version) || version.contains("-jre")
    return isStable.not()
}
