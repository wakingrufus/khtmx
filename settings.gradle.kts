plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "khtmx"
include(":khtmx-dsl")
include(":khtmx-spring")

includeBuild(".")
includeBuild("examples/example-spring-app"){
    name = "example-spring-app"
}
