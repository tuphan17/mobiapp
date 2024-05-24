plugins {
    alias(libs.plugins.androidApplication)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "edu.tacoma.uw.projecttcss450"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.tacoma.uw.projecttcss450"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        renderscriptTargetApi = 29
        renderscriptSupportModeEnabled =true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("com.android.volley:volley:1.2.1")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.volley)
    implementation ("androidx.navigation:navigation-fragment:2.4.2")
    implementation ("androidx.navigation:navigation-ui:2.4.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}