plugins {
    `java-library`
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

java { toolchain.languageVersion.set(JavaLanguageVersion.of(project.ext.get("javaToolchainVersion") as Int)) }

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(project.ext.get("javaToolchainVersion") as Int)
    }

    javadoc { options.encoding = Charsets.UTF_8.name() }
    processResources { filteringCharset = Charsets.UTF_8.name() }

    runServer { minecraftVersion("1.18.2") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation(project(":common")) // shaded
    implementation(project(":nms:1_18", "reobf")) // shaded (userdev)
}

bukkit {
    name = "MultiVersionPluginExample"
    description = "A simple example for multi version setups in plugins."
    apiVersion = "1.18"
    author = "Corey Shupe (FiXed)"
    main = "io.github.coreyshupe.multiversion.ExamplePlugin"
}