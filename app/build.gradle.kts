plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.example.marvelapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.marvelapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.lifecycleKtx)
    implementation(platform(libs.composeBom))
    implementation(libs.composeActivity)
    implementation(libs.composeRuntime)
    implementation(libs.composeUi)
    implementation(libs.composeGraphics)
    implementation(libs.composePreview)
    implementation(libs.composeDebugPreview)
    implementation(libs.composeDebugManifest)
    implementation(libs.composeMaterial)
    implementation(libs.composeMaterial3)
    implementation(libs.jUnit)
    implementation(libs.jUnitTest)
    implementation(libs.jUnitUi)
    implementation(libs.espressoTest)

    implementation(libs.navigation)
    implementation(libs.accomponistSystemUiController)

    implementation(libs.room)
    kapt(libs.roomCompiler)
    annotationProcessor(libs.roomCompiler)
    implementation(libs.roomKtx)

    implementation(libs.daggerHilt)
    kapt(libs.hilt)
    implementation(libs.hiltNavigation)

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":database"))
    implementation(project(":network"))
    implementation(project(":screens:full"))
    implementation(project(":screens:slide"))
}
