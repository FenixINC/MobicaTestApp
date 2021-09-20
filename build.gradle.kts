buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath(dependencyNotation = "com.android.tools.build:gradle:7.0.2")
        classpath(dependencyNotation = "com.google.gms:google-services:4.3.10")
        classpath(dependencyNotation = "com.squareup.sqldelight:gradle-plugin:1.5.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}