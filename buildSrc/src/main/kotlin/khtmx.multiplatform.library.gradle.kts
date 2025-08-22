import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    `maven-publish`
    kotlin("multiplatform")
    jacoco
    id("org.jlleitschuh.gradle.ktlint")
}
repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
    }
    jvm {
        mavenPublication {
            groupId = group as String
            pom {
                name = "${project.name}-jvm"
                description = "Sample application"
                url = "https://github.com/wakingrufus/khtmx"
                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "https://spdx.org/licenses/Apache-2.0.html"
                    }
                }
                developers {
                    developer {
                        id = "wakingrufus"
                        name = "John Burns"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/wakingrufus/khtmx.git"
                    developerConnection = "scm:git:ssh://github.com/wakingrufus/khtmx.git"
                    url = "http://github.com/wakingrufus/khtmx"
                }
            }
        }
    }
    js {
        outputModuleName.set(project.name)
        nodejs()

        mavenPublication {
            groupId = group as String
            pom {
                name = "${project.name}-js"
                description = "Sample application"
                url = "https://github.com/wakingrufus/khtmx"
                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "https://spdx.org/licenses/Apache-2.0.html"
                    }
                }
                developers {
                    developer {
                        id = "wakingrufus"
                        name = "John Burns"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/wakingrufus/khtmx.git"
                    developerConnection = "scm:git:ssh://github.com/wakingrufus/khtmx.git"
                    url = "http://github.com/wakingrufus/khtmx"
                }
            }
        }
    }
}
val jacocoReport = tasks.register("jacocoTestReport", JacocoReport::class) {
    dependsOn(tasks.withType(Test::class))
    sourceDirectories.setFrom(listOf("src/commonMain/kotlin", "src/jvmMain/kotlin"))
    classDirectories.setFrom(files(layout.buildDirectory.dir("classes/kotlin/jvm/main")))
    executionData.setFrom(layout.buildDirectory.file("jacoco/jvmTest.exec"))
}

tasks.withType<Test> {
    testLogging {
        info {
            events("passed", "skipped", "failed")
        }
    }
    finalizedBy(jacocoReport)
}
