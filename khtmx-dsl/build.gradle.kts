plugins {
    khtmx.multiplatform.library
    khtmx.publish.staging
}

project.description = "A multiplatform kotlin DSL for HTMX"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-html:0.12.0")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:2025.8.18")
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
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css-jvm:2025.8.18")
            }
        }
        jsMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-html-js:0.12.0")
            }
        }
        jsTest {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css-js:2025.8.18")
            }
        }
    }
}
