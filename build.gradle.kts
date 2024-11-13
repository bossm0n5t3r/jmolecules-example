plugins {
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "me.bossm0n5t3r.jmolecules"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

private val jmoleculesVersion = "0.23.0"

dependencies {
    implementation("org.jmolecules.integrations:jmolecules-starter-ddd:$jmoleculesVersion")
    implementation("org.jmolecules:kmolecules-ddd:1.9.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")

    testImplementation(kotlin("test"))
    testImplementation("org.jmolecules.integrations:jmolecules-starter-test:$jmoleculesVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.3.1")
}
