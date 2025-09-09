import org.jreleaser.model.Active

plugins {
    base
    id("jacoco-report-aggregation")
    id("org.jreleaser") version ("1.20.0")
}
project.description = "A multiplatform kotlin DSL for HTMX"
tasks.wrapper {
    gradleVersion = "9.0.0"
    distributionType = Wrapper.DistributionType.BIN
}
allprojects {
    group = "io.github.wakingrufus"
}
repositories {
    mavenCentral() // needed for jacoco
}
dependencies {
    subprojects.forEach {
        jacocoAggregation(project(":" + it.name))
    }
}

reporting {
    reports {
        val javaCodeCoverageReport by creating(JacocoCoverageReport::class){
            testSuiteName = "test"
        }

        // hack to get jacoco aggregate to work with KMM
        afterEvaluate{
            javaCodeCoverageReport.reportTask.get().apply {
                dependsOn(":khtmx-dsl:jacocoTestReport")
                executionData(executionData.plus(file("khtmx-dsl/build/jacoco/jvmTest.exec")))
            }
        }
    }
}

jreleaser {
    signing {
        active = Active.ALWAYS
        armored = true
        publicKey = System.getenv("PUBLIC_KEY")
        secretKey = System.getenv("PRIVATE_KEY")
        passphrase = System.getenv("PASSPHRASE")
    }
    release {
        github {
            repoOwner = "wakingrufus"
            host = "github.com"
            username = "wakingrufus"
            apiEndpoint = "https://api.github.com"
            token = System.getenv("GITHUB_TOKEN")
            enabled = true
            releaseName = "{{tagName}}"
            releaseNotes {
                enabled = true
            }
            changelog {
                enabled = false
            }
            skipTag = true
            sign = false
        }
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                    applyMavenCentralRules = true
                    username = System.getenv("SONATYPE_USER")
                    password = System.getenv("SONATYPE_PASS")
                    namespace.set("io.github.wakingrufus")
                    retryDelay = 30
                    maxRetries = 100
                    artifactOverride {
                        artifactId = "khtmx-dsl-js"
                        jar = false
                    }
                }
            }
        }
    }
}
project.tasks.named("jreleaserFullRelease") {
    dependsOn(subprojects.map { it.tasks.named("publish") })
}
