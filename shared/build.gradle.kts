plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        val coroutinesVersion = "1.6.4"
        val ktorVersion = "2.1.3"
        val kotlinXSerialization = rootProject.extra["kotlinXSerialization"]

        val commonMain by getting {
            dependencies{
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinXSerialization")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                //ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
                implementation("com.google.truth:truth:1.1.3")
                implementation("junit:junit:4.12")
            }
        }
        val androidMain by getting {
            dependencies{
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "dev.vaibhav.newsapp"
    compileSdk = 32
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter")
}
