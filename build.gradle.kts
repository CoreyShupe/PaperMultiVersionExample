allprojects {
    group = "io.github.coreyshupe"
    version = "0.0.1-SNAPSHOT"

    ext { set("javaToolchainVersion", 17) }

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}