plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.3.5"
}

java { toolchain.languageVersion.set(JavaLanguageVersion.of(project.ext.get("javaToolchainVersion") as Int)) }

tasks {
    assemble { dependsOn(reobfJar) }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(project.ext.get("javaToolchainVersion") as Int)
    }

    javadoc { options.encoding = Charsets.UTF_8.name() }
    processResources { filteringCharset = Charsets.UTF_8.name() }
}

dependencies {
    paperDevBundle("1.18.2-R0.1-SNAPSHOT")
    compileOnly(project(":common"))
}