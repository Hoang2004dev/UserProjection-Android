plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.userprotection"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.userprotection"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            // BuildConfig.DEBUG = true (auto)
            buildConfigField("boolean", "USE_UNSAFE_SSL", "true")
            buildConfigField("String", "API_ENV", "\"DEV\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // BuildConfig.DEBUG = false (auto)
            buildConfigField("boolean", "USE_UNSAFE_SSL", "false")
            buildConfigField("String", "API_ENV", "\"PROD\"")
        }
    }

    buildFeatures {
        buildConfig = true   // ✅ thêm dòng này
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // ✅ Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ✅ OkHttp Logging Interceptor (debug logs API)
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
