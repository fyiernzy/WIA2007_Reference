plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.fragmentbestpractice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fragmentbestpractice"
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation( "androidx.recyclerview:recyclerview:1.2.1")
    implementation( "androidx.fragment:fragment:1.4.1")

    // Jetpack Compose dependencies
    implementation( "androidx.compose.ui:ui:1.4.3")
    // Tooling support (Previews, etc.)
    implementation( "androidx.compose.ui:ui-tooling:1.4.3")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation( "androidx.compose.foundation:foundation:1.4.3")
    // Material Design
    implementation( "androidx.compose.material:material:1.4.3")
    // Material design icons
    implementation( "androidx.compose.material:material-icons-core:1.4.3")
    implementation( "androidx.compose.material:material-icons-extended:1.4.3")
    // Integration with activities
    implementation( "androidx.activity:activity-compose:1.4.0")
    // Integration with ViewModels
    implementation( "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    testImplementation( "junit:junit:4.13.2")
}
