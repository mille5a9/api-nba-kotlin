plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.mille5a9"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}
