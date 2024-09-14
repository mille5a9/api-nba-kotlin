group = "io.github.mille5a9"
version = "1.0"

plugins {
    id("api-sports.conventions")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
