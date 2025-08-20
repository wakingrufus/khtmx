plugins {
    khtmx.jvm
}

dependencies {
    api(project(":khtmx-dsl"))
    implementation(platform("org.springframework:spring-framework-bom:7.0.0-M7"))
    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-webmvc")
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.19.2"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")

    testImplementation(kotlin("reflect"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.0-M1")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.springframework.boot:spring-boot-starter-jetty:4.0.0-M1")
}
