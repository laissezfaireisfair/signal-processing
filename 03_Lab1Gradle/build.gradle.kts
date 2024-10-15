plugins {
    kotlin("jvm") version "2.0.20"
}

group = "and.signal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.kotlin.link")
}

dependencies {
    api("space.kscience:kmath-core:0.4.0")
    api("space.kscience:kmath-commons:0.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}