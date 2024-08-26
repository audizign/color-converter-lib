plugins {
    kotlin("jvm") version "2.0.0"
    application
    `maven-publish`
}

val mcVersion = "1.16"
group = "dev.idot"
version = "1.5"

fun RepositoryHandler.local() {
    maven(file("X:/AppData/Maven/repository"))
}

repositories {
    local()
    mavenCentral()

    maven("https://oss.sonatype.org/content/repositories/public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/public/")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("com.google.code.gson:gson:2.10")

    compileOnly("org.spigotmc:spigot-api:$mcVersion+")
    //compileOnly("net.md-5:bungeecord-api:$mcVersion+")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.24")
}

publishing {
    publications.create<MavenPublication>("plugin") {
        artifact(
            tasks.register("mainJar", Jar::class) {
                archiveClassifier.set("")
                from(sourceSets["main"].output)
            }.get()
        )
        artifact(
            tasks.register("sourceJar", Jar::class) {
                archiveClassifier.set("sources")
                from(sourceSets["main"].allSource)
            }.get()
        )
    }
    repositories {
        local()
    }
}
tasks {
    test {
        filter.excludeTestsMatching("Benchmark")
    }
}

kotlin {
    jvmToolchain(8)
}
