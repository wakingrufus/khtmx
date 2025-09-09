import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.maven

fun Project.publishToLocalStaging(name: String = "staging-deploy"){
    plugins.withId("maven-publish") {
        extensions.findByType<PublishingExtension>()?.apply {
            repositories.maven(project.rootProject.layout.buildDirectory.dir(name).get().asFile.toURI())
        }
    }
}
