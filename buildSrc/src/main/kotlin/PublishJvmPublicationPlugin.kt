import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class PublishJvmPublicationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("maven-publish")
        project.publishToLocalStaging()
        project.extensions.findByType<PublishingExtension>()?.apply {
            publications {
                create<MavenPublication>("maven") {
                    groupId = project.group.toString()
                    artifactId = project.name

                    from(project.components["java"])

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
        }
    }
}
