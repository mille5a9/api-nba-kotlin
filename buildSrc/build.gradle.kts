plugins {
    `kotlin-dsl`
}

apply("../gradle/loadProps.gradle")
val kotlinVersion: String by project
val dokkaVersion: String by project

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
}
