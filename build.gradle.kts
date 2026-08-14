plugins {
    war
    pmd
}

group = "org.forketyfork"
version = "0.0.1-SNAPSHOT"
description = "rainyhills"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

repositories {
    mavenCentral()
}

sourceSets {
    create("integrationTest") {
        java {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            srcDir("src/integration-test/java")
        }
        resources.srcDir("src/integration-test/resources")
    }
}

val integrationTestImplementation = configurations.getByName("integrationTestImplementation") {
    extendsFrom(configurations.testImplementation.get())
}

val integrationTestRuntimeOnly = configurations.getByName("integrationTestRuntimeOnly") {
    extendsFrom(configurations.testRuntimeOnly.get())
}

dependencies {
    providedCompile(libs.jakartaee.api)
    providedCompile(libs.jaxb.api)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    integrationTestImplementation(libs.junit.jupiter)
    integrationTestImplementation(libs.weld.junit5)
    integrationTestImplementation(libs.slf4j.simple)
}

val integrationTest = tasks.register<Test>("integrationTest") {
    description = "Runs integration tests"
    group = "verification"

    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath

    if (JavaVersion.current().isJava9Compatible) {
        jvmArgs(
            "--add-opens=java.base/java.lang=ALL-UNNAMED",
            "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
            "--add-opens=java.base/java.util=ALL-UNNAMED"
        )
    }

    shouldRunAfter(tasks.test)
}

tasks.check {
    dependsOn(integrationTest)
}

tasks.withType<Test> {
    useJUnitPlatform()
    reports.html.outputLocation = file("${project.layout.buildDirectory.get()}/reports/$name")
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.test {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

integrationTest.configure {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.war {
    archiveFileName = "rainyhills.war"
}

pmd {
    isConsoleOutput = true
    toolVersion = libs.versions.pmd.get()
    ruleSetFiles = files("pmd-ruleset.xml")
}

val stage = tasks.register("stage") {
    dependsOn(tasks.build, tasks.clean)
}

tasks.build {
    mustRunAfter(tasks.clean)
}

val copyToTarget = tasks.register<Copy>("copyToTarget") {
    into("target")
    from("${layout.buildDirectory.get()}/libs")
}

stage {
    dependsOn(copyToTarget)
}
