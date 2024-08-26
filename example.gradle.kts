plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "your.group"
version = "0.1"

repositories {
    mavenLocal()
}

dependencies {
    implementation("dev.idot:color-converter-lib:1.4")
}

tasks {
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