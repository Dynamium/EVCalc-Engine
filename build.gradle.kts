import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("maven-publish")
}
group = "org.dynamium.evcalc"
version = "1.0-beta1.2-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

publishing {
    repositories {
        /*maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Dynamium/EVCalc")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("EVCALC_GHP_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("EVCALC_GHP_TOKEN")
            }
        }*/
        maven { // For local maven deployment
            url = uri("$buildDir/repo")
        }
    }
    publications {
        /*create<MavenPublication>("evcalc") {
            from(components["java"])
        }*/
        create<MavenPublication>("evcalc-local") { // For local maven deployment
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.slf4j:slf4j-simple:1.7.26")
    implementation("io.github.microutils:kotlin-logging:1.12.0")
    testImplementation("io.kotest:kotest-runner-junit5:4.3.0") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:4.3.0") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:4.3.0") // for kotest property test
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
