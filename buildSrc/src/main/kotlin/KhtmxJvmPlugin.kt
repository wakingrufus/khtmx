import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.findByType

class KhtmxJvmPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("java-library")
        project.plugins.apply(ResolutionRepositoriesPlugin::class.java)
        project.plugins.apply("org.jetbrains.kotlin.jvm")
        project.plugins.apply(KhtmxJvmTestingPlugin::class.java)
        project.plugins.apply(PublishJvmPublicationPlugin::class.java)
        project.extensions.findByType<JavaPluginExtension>()?.apply {
            withJavadocJar()
            withSourcesJar()
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
        }
    }
}
