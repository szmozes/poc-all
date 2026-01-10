rootProject.name = "poc-all"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenCentral()
    }
}

include(":auth-engine")
include(":benchmark")
include(":reactive")
include(":jpa:filter-specification")
