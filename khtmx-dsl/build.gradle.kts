import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    khtmx.multiplatform.library
}

project.description = "A multiplatform kotlin DSL for HTMX"

kotlin {
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
    }
    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-html:0.12.0")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
            }
        }
        jvmMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")
            }
        }
        jvmTest {
            dependencies {
                implementation("org.assertj:assertj-core:3.27.3")
            }
        }
        jsMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-html-js:0.12.0")
            }
        }
    }
}

kotlin {
    jvmToolchain(11)

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
tasks.withType<Test> {
    testLogging {
        info {
            events("passed", "skipped", "failed")
        }
    }
}
