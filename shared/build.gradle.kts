import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(module = "multiplatform")
    kotlin(module = "plugin.serialization") version "1.5.10"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    android()

    ios() {
        compilations {
            val main by getting {
                kotlinOptions.freeCompilerArgs = listOf("-Xobjc-generics")
            }
        }
    }

    val ktorVersion = "1.5.4"
    val sqlDelightVersion = "1.5.0"
    val coroutinesVersion = "1.5.0-native-mt"
    val serializationVersion = "1.0.1"
    val kodeinVersion = "7.5.0"
    val settingsVersion = "0.8"
    val lifecycleVersion = "2.4.0-alpha02"

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Serialization
                implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

                // Network
                implementation(dependencyNotation = "io.ktor:ktor-client-core:${ktorVersion}")
                implementation(dependencyNotation = "io.ktor:ktor-client-json:${ktorVersion}")
                implementation(dependencyNotation = "io.ktor:ktor-client-serialization:${ktorVersion}")
                implementation(dependencyNotation = "io.ktor:ktor-client-logging:$ktorVersion")

                // Database
                implementation(dependencyNotation = "com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation(dependencyNotation = "com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")

                // Coroutines
                implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")

                // DI
                implementation(dependencyNotation = "org.kodein.di:kodein-di:${kodeinVersion}")

                // KMM Settings
                implementation(dependencyNotation = "com.russhwolf:multiplatform-settings:$settingsVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(simpleModuleName = "test-common"))
                implementation(kotlin(simpleModuleName = "test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Lifecycle
                implementation(dependencyNotation = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

                // Network
                api(dependencyNotation = "io.ktor:ktor-client-okhttp:${ktorVersion}")

                // Database
                implementation(dependencyNotation = "com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin(simpleModuleName = "test-junit"))
                implementation(dependencyNotation = "junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                // Network
                implementation(dependencyNotation = "io.ktor:ktor-client-ios:${ktorVersion}")

                // Database
                implementation(dependencyNotation = "com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile(srcPath = "src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 30
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

sqldelight {
    database("MobicaDatabase") {
        packageName = "com.example.mobicatestapp.cache"
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)