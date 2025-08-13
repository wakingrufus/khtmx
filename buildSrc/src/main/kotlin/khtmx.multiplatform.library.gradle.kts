plugins {
    `maven-publish`
    kotlin("multiplatform")
}
repositories {
    mavenCentral()
}
publishing {
    repositories {
        maven {
            url = rootProject.layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}

kotlin {
//    compilerOptions {
//        languageVersion.set(KotlinVersion.KOTLIN_2_0)
//        apiVersion.set(KotlinVersion.KOTLIN_2_0)
//    }
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