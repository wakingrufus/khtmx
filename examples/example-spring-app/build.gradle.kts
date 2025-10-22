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
    implementation(platform(libs.spring.bom))
    implementation(libs.spring.boot.autoconfigure)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jetty)
    implementation("io.github.wakingrufus:khtmx-dsl")
    implementation("io.github.wakingrufus:khtmx-spring")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    implementation("ch.qos.logback:logback-classic:1.5.18")

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.spring.boot.starter.restclient)
    testImplementation("org.assertj:assertj-core:3.27.3")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("6.0.0")
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
