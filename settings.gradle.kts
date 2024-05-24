pluginManagement {
    repositories {
        google()
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
    versionCatalogs{
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "MarvelApp"
include(":app")
include(":core")
include(":network")
include(":screens")
include(":core-ui")
include(":full")
include(":screens:slide")
include(":screens:full")
include(":database")
include(":navigation")
