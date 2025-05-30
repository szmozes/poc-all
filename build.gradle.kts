import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.kotlin.dsl.withType

group = "hu.szmozes"
version = "0.0.1-SNAPSHOT"

plugins {
    alias(libs.plugins.gradle.java.library)
    alias(libs.plugins.ben.manes.versions)
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring) apply false
    alias(libs.plugins.lombok) apply false
    alias(libs.plugins.spotless)
}

spotless {
    format("misc") {
        target(".gitignore", "*.yml", "*.md", "*.Dockerfile")

        leadingTabsToSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlin {
        ktlint()
    }
    kotlinGradle {
        leadingTabsToSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA", "JRE").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
