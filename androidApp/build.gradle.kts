plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.vaibhav.newsapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "dev.vaibhav.newsapp.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {

    val hiltVersion = rootProject.extra["hiltVersion"]
    val lifecycleVersion = rootProject.extra["lifecycleVersion"]
    val composeVersion = rootProject.extra["composeVersion"]
    val navVersion = rootProject.extra["navVersion"]

    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    implementation("com.google.code.gson:gson:2.10")

    //hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    //navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")
}

kapt{
    correctErrorTypes = true
}