import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.4.10"
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.4.10.2"
}
group = "org.dynamium.evcalc"
version = "1.0-dev8-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

publishing {
    repositories {
        maven {
            credentials {
                username = System.getenv("jb_packages_username")
                password = System.getenv("jb_packages_password")
            }

            url = uri("https://maven.pkg.jetbrains.space/dynamium/p/evc/maven")
        }
        maven {
            credentials {
                username = System.getenv("jb_packages_username")
                password = System.getenv("jb_packages_password")
            }

            url = uri("https://maven.pkg.jetbrains.space/dynamium/p/evc/maven-snapshots")
        }
    }
    publications {
        create<MavenPublication>("evcalc") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            description = "EVCalc Engine is an engine for calculating mileage, battery consumption, and more."

            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.3")
    dokkaHtmlPlugin("org.jetbrains.dokka:dokka-base:1.4.10.2")
    testImplementation("io.kotest:kotest-runner-junit5:4.3.1") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:4.3.1") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:4.3.1") // for kotest property test
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        kotlinOptions.includeRuntime = true
    }
}
