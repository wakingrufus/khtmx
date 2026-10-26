plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
    gradlePluginPortal()
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.20")
    implementation("org.jlleitschuh.gradle.ktlint:org.jlleitschuh.gradle.ktlint.gradle.plugin:14.+")
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
