import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.signing

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
    signing
}


val ossrhUsername: String by project
val ossrhPassword: String by project

java {
    withJavadocJar()
}

val sourcesJar by tasks.registering(Jar::class) {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

afterEvaluate {
    tasks.getByName("generateMetadataFileForMavenJavaPublication") {
        dependsOn(sourcesJar)
    }
}

lateinit var pomName: String
lateinit var pomDesc: String
lateinit var pomUrl: String

ext {
    pomName = ""
    pomDesc = ""
    pomUrl = ""
}

publishing {
    repositories {
        maven {
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
            pom {
                name.set(pomName)
                description.set(pomDesc)
                url.set(pomUrl)
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("mille5a9")
                        name.set("Andrew Miller")
                        email.set("atmiller192@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/mille5a9/api-sports-kotlin.git")
                    developerConnection.set("scm:git:https://github.com/mille5a9/api-sports-kotlin.git")
                    url.set("https://github.com/mille5a9/api-sports-kotlin")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
    val keyId: String by project
    val secretKeyRingFile: String by project
    val password: String by project
}
