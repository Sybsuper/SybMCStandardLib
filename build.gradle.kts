plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.dokka") version "1.9.20"
    id("jacoco")
    id("maven-publish")
}

group = "com.sybsuper"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://nexuslite.gcnt.net/repos/other/")
}

dependencies {
    implementation("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    implementation("dev.folia:folia-api:1.20.4-R0.1-SNAPSHOT")
    implementation("com.tcoded:FoliaLib:0.3.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

val javaComponent: SoftwareComponent = components["java"]

tasks {
    build {
        finalizedBy(shadowJar)
        finalizedBy(dokkaHtml)
        finalizedBy(jacocoTestReport)
    }

    test {
        useJUnitPlatform()
    }

    jacocoTestReport {
        dependsOn(test)

        reports {
            xml.required = true
            html.required = true
        }

        finalizedBy(jacocoTestCoverageVerification)
    }

    jacocoTestCoverageVerification {
        dependsOn(jacocoTestReport)
        violationRules {
            rule {
                limit {
                    counter = "BRANCH"
                    minimum = "0.6".toBigDecimal() // todo: increase this later
                }
            }
        }
    }

    dokkaHtml.configure {
        dokkaSourceSets {
            configureEach {
                samples.from("$projectDir/src/test/kotlin/samples/")
            }
        }
    }

    shadowJar {
        dependencies {
            include(dependency("com.tcoded:FoliaLib:0.3.1"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        }

        relocate("com.tcoded.folialib", "com.sybsuper.sybmcstandardlib.folialib")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = "sybmcstandardlib"

                from(javaComponent)
            }
        }
    }
}
val targetJavaVersion = 17
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }

    withJavadocJar()
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release.set(targetJavaVersion)
    }
}
