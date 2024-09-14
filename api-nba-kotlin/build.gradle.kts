plugins {
    id("api-sports.conventions")
    id("api-sports.publishing")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
}

testing {
    suites {
        // Configure the built-in test suite
        @Suppress("UnstableApiUsage") val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.9.20")
        }
    }
}

ext.set("pomName", "api-nba-kotlin")
ext.set("pomDesc", "Unofficial wrapper for the API-SPORTS NBA API")
ext.set("pomUrl", "https://github.com/mille5a9/api-sports-kotlin")
