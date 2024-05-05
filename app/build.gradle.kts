import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.myaxa.effective_mobile_tickets_search"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myaxa.effective_mobile_tickets_search"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val properties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.dagger)
    implementation(libs.jakarta.inject.api)
    kapt(libs.dagger.compiler)

    implementation(project(":domain"))
    implementation(project(":features:main-screen"))
    implementation(project(":features:search-api"))
    implementation(project(":features:search-impl"))

    implementation(project(":data"))
    implementation(project(":network"))
    implementation(project(":local"))

    implementation(project(":common"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}