plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.21")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
}
