rootProject.name = "poc-all"

pluginManagement {
    repositories {
        maven {
            url = uri("http://localhost:7999/repository/maven-public/")
            isAllowInsecureProtocol = true
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        maven {
            url = uri("http://localhost:7999/repository/maven-public/")
            isAllowInsecureProtocol = true
        }
    }
}

include(":filter-specification")
include(":auth-engine")
