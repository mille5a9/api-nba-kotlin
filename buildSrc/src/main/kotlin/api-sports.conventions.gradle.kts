import gradle.kotlin.dsl.accessors._adc73c88e0a124c97e28fdd97139246a.api
import gradle.kotlin.dsl.accessors._adc73c88e0a124c97e28fdd97139246a.implementation
import gradle.kotlin.dsl.accessors._adc73c88e0a124c97e28fdd97139246a.testImplementation
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
}

val projectGroup: String by project
val projectVersion: String by project
val ktorVersion: String by project

group = projectGroup
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {
    api("io.ktor:ktor-client-core:$ktorVersion")

    implementation("io.ktor:ktor-serialization-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
}
