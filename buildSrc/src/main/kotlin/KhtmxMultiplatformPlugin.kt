import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

class KhtmxMultiplatformPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(ResolutionRepositoriesPlugin::class.java)
        project.plugins.apply("maven-publish")
        project.plugins.apply("org.jetbrains.kotlin.multiplatform")
        project.plugins.apply("jacoco")
        project.plugins.apply("org.jlleitschuh.gradle.ktlint")
        project.plugins.apply("org.jetbrains.dokka")
        project.publishToLocalStaging()
        project.extensions.findByType<KotlinMultiplatformExtension>()?.apply {
            jvmToolchain(11)
            compilerOptions {
                languageVersion.set(KotlinVersion.KOTLIN_2_0)
                apiVersion.set(KotlinVersion.KOTLIN_2_0)
            }
            jvm {
                mavenPublication {
                    groupId = project.group as String
                    pom {
                        name = "${project.name}-jvm"
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
                    groupId = project.group as String
                    pom {
                        name = "${project.name}-js"
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
        val jacocoReport = project.tasks.register("jacocoTestReport", JacocoReport::class) {
            dependsOn(project.tasks.withType(Test::class))
            sourceDirectories.setFrom(listOf("src/commonMain/kotlin", "src/jvmMain/kotlin"))
            classDirectories.setFrom(project.files(project.layout.buildDirectory.dir("classes/kotlin/jvm/main")))
            executionData.setFrom(project.layout.buildDirectory.file("jacoco/jvmTest.exec"))
        }

        project.tasks.withType<Test> {
            testLogging {
                info {
                    events("passed", "skipped", "failed")
                }
            }
            finalizedBy(jacocoReport)
        }
        val javadocJar = project.tasks.register<Jar>("javadocJar") {
            archiveClassifier.set("javadoc")
            from(project.tasks.named("dokkaGenerateHtml"))
        }
        project.extensions.findByType<PublishingExtension>()?.apply {
            publications.filterIsInstance<MavenPublication>().forEach {
                it.pom {
                    url.set("https://github.com/wakingrufus/khtmx")
                    project.afterEvaluate {
                        this@pom.description.set(project.description)
                    }
                }
                it.artifact(javadocJar.get())
            }
        }
    }
}
