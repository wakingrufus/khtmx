plugins {
    khtmx.jvm
}

dependencies {
    api(project(":khtmx-dsl"))
    implementation(platform(libs.spring.bom))
    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-webmvc")
    implementation(platform("tools.jackson:jackson-bom:3.0.0"))
    implementation("tools.jackson.core:jackson-databind")

    testImplementation(kotlin("reflect"))
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation(libs.spring.boot.starter.jetty)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.spring.boot.starter.web)
    testImplementation(libs.spring.boot.starter.restclient)
    testImplementation(libs.spring.test)
}
