plugins {
    application
    id("org.springframework.boot") version ("4.+")
    kotlin("jvm") version ("2.2.0")
}
repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(platform("org.springframework:spring-framework-bom:7.0.0-M7"))
    implementation("org.springframework:spring-beans")
    implementation("org.springframework:spring-webmvc")
    implementation("org.springframework.boot:spring-boot-autoconfigure:4.0.0-M1")
    implementation("org.springframework.boot:spring-boot-starter-jetty:4.0.0-M1")
    implementation("org.springframework.boot:spring-boot-starter-web:4.0.0-M1")
    implementation("io.github.wakingrufus:khtmx-dsl")
    implementation("io.github.wakingrufus:khtmx-spring")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    implementation("ch.qos.logback:logback-classic:1.5.18")

    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.0-M1")
    testImplementation("org.springframework.boot:spring-boot-starter-restclient:4.0.0-M1")
    testImplementation("org.assertj:assertj-core:3.27.3")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass = "ExampleApplicationKt"
}