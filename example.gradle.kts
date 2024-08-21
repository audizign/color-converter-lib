plugins {
    kotlin("jvm") version "1.9.24"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.idot"
version = "1.1"

repositories {
    mavenLocal()
}

dependencies {
    implementation("dev.idot:color-converter-lib:0.2")
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