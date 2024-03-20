import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
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

        val localProperties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }
        val marvelBaseUrl by localProperties
        val marvelPublicKey by localProperties
        val marvelPrivateKey by localProperties

        buildConfigField("String", "MARVEL_BASE_URL", "$marvelBaseUrl")
        buildConfigField("String", "MARVEL_PUBLIC_KEY", "$marvelPublicKey")
        buildConfigField("String", "MARVEL_PRIVATE_KEY", "$marvelPrivateKey")

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

    implementation(libs.coil)
    implementation(libs.navigation)
    implementation(libs.accomponistSystemUiController)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.okhttp)
    implementation(libs.interceptor)
}