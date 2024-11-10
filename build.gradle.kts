plugins {
    kotlin("jvm") version "2.0.21"
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

    testImplementation(kotlin("test"))
    testImplementation("org.jmolecules.integrations:jmolecules-starter-test:$jmoleculesVersion")
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
