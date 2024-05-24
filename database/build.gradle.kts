plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.example.database"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.lifecycleKtx)
    implementation(platform(libs.composeBom))
    implementation(libs.composeActivity)

    implementation(libs.room)
    kapt(libs.roomCompiler)
    annotationProcessor(libs.roomCompiler)
    implementation(libs.roomKtx)

    implementation(libs.daggerHilt)
    kapt(libs.hilt)
    implementation(libs.hiltNavigation)

    implementation(project(":core-ui"))

}