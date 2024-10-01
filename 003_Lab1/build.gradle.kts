plugins {
    kotlin("jvm") version "2.0.10"
}

group = "and.signal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.kotlin.link")
}

dependencies {
    implementation(group = "com.github.yannrichet", name = "JMathPlot", version = "1.0.1")
    implementation(project(":integrate"))
    api("space.kscience:kmath-core:0.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}