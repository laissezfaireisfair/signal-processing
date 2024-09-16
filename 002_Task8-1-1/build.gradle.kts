plugins {
    kotlin("jvm") version "2.0.10"
}

group = "and.signal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(group = "com.github.yannrichet", name = "JMathPlot", version = "1.0.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}