plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("com.google.devtools.ksp")
}

val projectGroup: String by project
val projectVersion: String by project
val ktorVersion: String by project
val kotlinVersion: String by project
val autoDslVersion: String by project

group = projectGroup
version = projectVersion

repositories {
    mavenCentral()
    google()
}

dependencies {
    api("io.ktor:ktor-client-core:$ktorVersion")

    implementation("io.ktor:ktor-serialization-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("com.faendir.kotlin.autodsl:annotations:$autoDslVersion")
    ksp("com.faendir.kotlin.autodsl:processor:$autoDslVersion")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
}

testing {
    suites {
        @Suppress("UnstableApiUsage") val test by getting(JvmTestSuite::class) {
            useKotlinTest(kotlinVersion)
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
