plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}
repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

// Koin DI
val koinVersion = "3.1.1"
// Kotlin Coroutines
val kotlinCoroutinesVersion = "1.5.2"
// Compose
val composeVersion = "1.0.2"
val composeActivityVersion = "1.3.0-rc01"
val composeViewModelVersion = "1.0.0-alpha07"
val composeNavigationVersion = "2.4.0-alpha04"
val composeConstraintVersion = "1.0.0-alpha08"
val accompanistVersion = "0.18.0"
// Shimmer
val shimmerVersion = "0.16.0"
// Coil Image Loader
val coilVersion = "1.3.2"

dependencies {
    implementation(project(":shared"))

    // AndroidX
    implementation(dependencyNotation = "androidx.core:core-ktx:1.6.0")
    implementation(dependencyNotation = "androidx.appcompat:appcompat:1.3.1")
    implementation(dependencyNotation = "com.google.android.material:material:1.4.0")

    // DI Koin
    implementation(dependencyNotation = "io.insert-koin:koin-android:$koinVersion")
    implementation(dependencyNotation = "io.insert-koin:koin-androidx-compose:$koinVersion")

    // Kotlin Coroutines
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion")

    // Compose
    implementation(dependencyNotation = "androidx.compose.compiler:compiler:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.runtime:runtime:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.ui:ui:$composeVersion")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(dependencyNotation = "androidx.compose.foundation:foundation:$composeVersion")
    // Material Design, Icons
    implementation(dependencyNotation = "androidx.compose.material:material:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-core:$composeVersion")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-extended:$composeVersion")
    // Integration with activities
    implementation(dependencyNotation = "androidx.activity:activity-compose:$composeActivityVersion")
    // Integration with ViewModels
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelVersion")
    // Integration with observables
    implementation(dependencyNotation = "androidx.compose.runtime:runtime-livedata:$composeVersion")
    // Compose Animations
    implementation(dependencyNotation = "androidx.compose.animation:animation:$composeVersion")
    // Integration with Navigation
    implementation(dependencyNotation = "androidx.navigation:navigation-compose:$composeNavigationVersion")
    // Compose Constraint
    implementation(dependencyNotation = "androidx.constraintlayout:constraintlayout-compose:$composeConstraintVersion")
    // Accompanist
    implementation(dependencyNotation = "com.google.accompanist:accompanist-insets:$accompanistVersion")
    implementation(dependencyNotation = "com.google.accompanist:accompanist-placeholder:$shimmerVersion")
    implementation(dependencyNotation = "com.google.accompanist:accompanist-pager:$accompanistVersion")

    // Coil
    implementation(dependencyNotation = "io.coil-kt:coil-compose:$coilVersion")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.example.mobikatestapp"
        minSdk = 26
        targetSdk = 30
        versionCode = 1
        versionName = "1.0.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-rc02"
        kotlinCompilerVersion = "1.5.10"
    }
}