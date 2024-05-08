pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Effective Mobile Tickets Search"
include(":app")
include(":common")

include(":domain")

include(":network")
include(":data")
include(":local")

include(":features:main-screen")
include(":features:search-api")
include(":features:search-impl")
include(":features:search-selected-country-impl")
include(":features:search-selected-country-api")
include(":features:tickets-api")
include(":features:tickets-impl")
