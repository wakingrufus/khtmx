plugins {
    `java-library`
    `jvm-test-suite`
    jacoco
    id("jacoco-report-aggregation")
    `maven-publish`
    kotlin("jvm")
}
repositories {
    mavenCentral()
}
project.tasks.named("build") {
    dependsOn("jacocoTestReport")
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter()
        }
    }
}
java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name

            from(components["java"])

            pom {
                name = project.name
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

    repositories {
        maven {
            url = rootProject.layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}
