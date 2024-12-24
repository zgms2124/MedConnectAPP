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
        google() // 使用 Google 仓库
        mavenCentral() // 使用 Maven Central 仓库
        // 其他需要的仓库
    }
}

rootProject.name = "My Application"
include(":app")
