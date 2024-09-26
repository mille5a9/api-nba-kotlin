plugins {
    `kotlin-dsl`
}

apply("../gradle/loadProps.gradle")
val kotlinVersion: String by project
val dokkaVersion: String by project
val kspVersion: String by project

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")

    runtimeOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$kspVersion")
}
