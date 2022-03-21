pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "paper-multi-version-example"

include(":common", ":nms:1_18", ":plugin")
