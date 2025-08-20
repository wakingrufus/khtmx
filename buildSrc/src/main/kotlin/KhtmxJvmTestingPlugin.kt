import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.named
import org.gradle.testing.base.TestingExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

class KhtmxJvmTestingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("jacoco")
        project.extensions.findByType<TestingExtension>()?.apply {
            suites {
                named<JvmTestSuite>("test") {
                    useJUnitJupiter()
                }
            }
        }
        project.tasks.named<JacocoReport>("jacocoTestReport") {
            reports {
                xml.required = true
            }
        }
        project.tasks.named("test") {
            finalizedBy("jacocoTestReport")
        }
    }
}
