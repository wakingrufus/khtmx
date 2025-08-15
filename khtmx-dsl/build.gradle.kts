plugins {
    khtmx.multiplatform.library
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

