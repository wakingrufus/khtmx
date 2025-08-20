import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories

class ResolutionRepositoriesPlugin: Plugin<Project> {
    override fun apply(project: Project) {
       project.repositories {
           mavenCentral()
       }
    }
}
