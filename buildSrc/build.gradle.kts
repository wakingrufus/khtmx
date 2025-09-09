plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
    gradlePluginPortal()
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0")
    implementation("org.jlleitschuh.gradle.ktlint:org.jlleitschuh.gradle.ktlint.gradle.plugin:13.+")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:2.+")
}
gradlePlugin {
    plugins {
        create("publish.staging") {
            id = "khtmx.publish.staging"
            implementationClass = "PublishStagingPlugin"
        }
        create("jvm") {
            id = "khtmx.jvm"
            implementationClass = "KhtmxJvmPlugin"
        }
        create("multiplatform") {
            id = "khtmx.multiplatform"
            implementationClass = "KhtmxMultiplatformPlugin"
        }
    }
}
