description = "jooq"

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.jooq)
}

sourceSets {
    main {
        kotlin {
            srcDir("build/generated-sources/jooq")
        }
    }
}

dependencies {
    implementation(platform(libs.spring.cloud.dependencies))
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.jooq)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.liquibase)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib)
    runtimeOnly(libs.postgres)
    testImplementation(libs.spring.boot.starter.test)
    jooqCodegen(libs.jooq.meta.extensions.liquibase)
    jooqCodegen(libs.liquibase.core)
    jooqCodegen(files("src/main/resources"))
}

jooq {
    configuration {
        logging = org.jooq.meta.jaxb.Logging.WARN
        generator {
            name = "org.jooq.codegen.KotlinGenerator"
            database {
                name = "org.jooq.meta.extensions.liquibase.LiquibaseDatabase"
                properties {
                    property {
                        key = "scripts"
                        value = "/db/changelog/db.changelog-master.yaml"
                    }
                    property {
                        key = "includeResources"
                        value = "true"
                    }
                }
                inputSchema = ""
            }
            target {
                packageName = "hu.szmozes.jooq.generated"
            }
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("jooqCodegen")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
