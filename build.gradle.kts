import org.jetbrains.compose.desktop.application.dsl.TargetFormat


val roomVersion = "2.7.1"
val sqliteVersion = "2.5.0-alpha01"

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("androidx.room").version("2.7.1")
    id("com.google.devtools.ksp").version("2.1.21-2.0.1")
}


group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.sqlite:sqlite-bundled:$sqliteVersion")
    implementation("androidx.room:room-gradle-plugin:$roomVersion")
    implementation("androidx.room:room-compiler:$roomVersion")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TasksProject"
            packageVersion = "1.0.0"
        }
    }
}
