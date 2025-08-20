import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.maven

class PublishStagingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("maven-publish")
        project.extensions.findByType<PublishingExtension>()?.apply {
            repositories.maven(project.rootProject.layout.buildDirectory.dir("staging-deploy").get().asFile.toURI())
        }
    }
}
