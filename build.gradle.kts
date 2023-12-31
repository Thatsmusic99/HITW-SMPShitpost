plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.2.0"
}

group = "io.github.thatsmusic99"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    runServer {
        minecraftVersion("1.20.2")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}