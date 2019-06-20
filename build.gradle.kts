plugins {
    kotlin("multiplatform") version "1.3.31"
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kittinunf/maven")
}

kotlin {
    // For ARM, preset function should be changed to iosArm32() or iosArm64()
    // For Linux, preset function should be changed to e.g. linuxX64()
    // For MacOS, preset function should be changed to e.g. macosX64()
    macosX64("HelloWorld") {
        binaries {
            // Comment the next section to generate Kotlin/Native library (KLIB) instead of executable file:
            executable("HelloWorldApp") {
                // Change to specify fully qualified name of your application's entry point:
                entryPoint = "de.lunchtime.main"
            }
        }


//        val main by compilations.getting

        compilations.all {
            val CoreLocation by cinterops.creating {
                defFile(project.file("src/nativeInterop/cinterop/CoreLocation.def"))
            }
        }

    }
    sourceSets {
        all {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.2.1")
            }
        }
    }
}

// Use the following Gradle tasks to run your application:
// :runHelloWorldAppReleaseExecutableHelloWorld - without debug symbols
// :runHelloWorldAppDebugExecutableHelloWorld - with debug symbols
