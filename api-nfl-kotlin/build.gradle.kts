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

ext.set("pomName", "api-nfl-kotlin")
ext.set("pomDesc", "Unofficial wrapper for the API-SPORTS NFL API")
ext.set("pomUrl", "https://github.com/mille5a9/api-sports-kotlin")
