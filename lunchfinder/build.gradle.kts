
plugins {
    kotlin("multiplatform") version "1.3.30"
    kotlin("xcode-compat") version "0.1"
}

repositories {
    mavenCentral()
}

kotlin {
    xcode {
        setupApplication("lunchfinder")
    }

    // This is a workaround that fixes this issue: https://github.com/JetBrains/kotlin-native/issues/3041
    macosX64 {
        val lunchfinderMain by compilations.creating {
            val coreLocation by cinterops.creating {
                defFile(project.file("CoreLocation.def"))
                packageName("platform.CoreLocation")
            }
        }
    }

    sourceSets {
        val lunchfinderTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
                implementation("junit:junit:4.12")
            }
        }
        all {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.1.1")
                implementation("io.ktor:ktor-client-core:1.2.1")
                implementation("io.ktor:ktor-client-core-native:1.2.1")
                implementation("io.ktor:ktor-client-curl:1.2.1")
            }
        }
    }
}



