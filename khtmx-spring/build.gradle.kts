plugins {
    khtmx.jvm
}

dependencies {
    api(project(":khtmx-dsl"))
    implementation(platform(libs.spring.bom))
    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-webmvc")
    implementation(platform("tools.jackson:jackson-bom:3.0.0-rc10"))
    implementation("tools.jackson.core:jackson-databind")

    testImplementation(kotlin("reflect"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.0-M1")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.springframework.boot:spring-boot-starter-jetty:4.0.0-M1")
}
