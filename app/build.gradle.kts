plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//  TODO 23 - IMPLEMENT PARCELABLE (SEND DATA BUNDLE)
    id("kotlin-parcelize")
//  TODO 24 - IMPLEMENT KOTLIN SYMBOL PROCESSING
    id("com.google.devtools.ksp")
}

android {
    compileSdkPreview = "UpsideDownCake"
    namespace = "com.nori.muslimmediaapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nori.muslimmediaapp"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // TODO 2 - ADDING SPLASHSCREEN FOR API 21
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")
    // TODO 8 - ADDING COORDINATOR LAYOUT DEPENDENCIES
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    // TODO 25 - ADDING GLIDE & PICASSO (Image loader) DEPENDENCIES
    implementation("com.squareup.picasso:picasso:2.8")
    // TODO 26 - ADDING MOSHI (JSON Converter) DEPENDENCIES
    //noinspection GradleDependency
    implementation("com.squareup.moshi:moshi:1.14.0")
    // TODO 27 - ADDING KOTLIN CODEGENERATOR DEPENDENCIES
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    // TODO 28 - ADDING RETROFIT (Getting API Key, HTTP Client with okhttp, Retrieve data via REST-based webservice) DEPENDENCIES
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}