plugins {
    kotlin("jvm") version "1.9.24"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val mcVersion = "1.16"
group = "dev.idot"
version = "0.1"

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://repo.codemc.io/repository/nms/")
    maven("https://repo.codemc.io/repository/maven-public/")

    maven("https://oss.sonatype.org/content/repositories/public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/public/")
}

dependencies {
    implementation("dev.idot:color-converter-lib:0.2") // this is a local dependency

    compileOnly("org.bukkit:bukkit:$mcVersion+")
    compileOnly("org.bukkit:craftbukkit:$mcVersion+")
    compileOnly("org.spigotmc:spigot-api:$mcVersion+")
}

tasks {
    // if you're making a spigot plugin
    processResources {
        filesMatching("plugin.yml") {
            expand(
                "displayName" to project.name,  // name: ${displayName}
                "version" to version            // version: ${version}
            )
        }
    }
    // this is for shading the dependencies
    shadowJar {
        archiveClassifier.set("")
        dependencies {
            include(dependency("dev.idot:color-converter-lib"))
            exclude("colors.json")              // If you are not using this file
        }
    }
}

kotlin {
    jvmToolchain(8)
}