plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.compose") version "1.3.1"
}

group = "com.marcfearby"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    testImplementation(kotlin("test-junit"))
    testImplementation(kotlin("test-common"))
    testImplementation("org.jetbrains.compose.ui:ui-test-junit4:1.4.0")
    implementation("io.kotest:kotest-assertions-core:5.5.5")
}

compose.desktop {
    application {
        mainClass = "com.marcfearby.MainKt"
    }
}