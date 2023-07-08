plugins {
    kotlin("jvm") version "1.8.20"
    id("org.jetbrains.compose") version "1.4.0"
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
    implementation("org.jetbrains.compose.components:components-splitpane:1.4.0")
    implementation("io.insert-koin:koin-core:3.4.0")
    implementation("io.insert-koin:koin-compose:1.0.1")
    implementation("uk.co.caprica:vlcj:4.8.2")

    testImplementation(kotlin("test-junit"))
    testImplementation(kotlin("test-common"))
    testImplementation("org.jetbrains.compose.ui:ui-test-junit4:1.4.0")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    testImplementation("io.insert-koin:koin-test:3.4.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.4.0")
}

compose.desktop {
    application {
        mainClass = "com.marcfearby.MainKt"
    }
}