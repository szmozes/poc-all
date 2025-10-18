description = "benchmark"

plugins {
    alias(libs.plugins.java)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
