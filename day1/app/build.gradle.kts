plugins {
    alias(libs.plugins.android.application)
}
apply("../manifest.gradle.kts")

android {
    namespace = "fan.akua.day1"
    compileSdk = extra["targetSdk"] as Int

    defaultConfig {
        applicationId = "fan.akua.day1"
        minSdk = extra["minSdk"] as Int
        targetSdk = extra["targetSdk"] as Int
        versionCode = extra["versionCode"] as Int
        versionName = extra["versionName"] as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}