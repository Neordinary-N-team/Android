plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

}

configurations.all {
    resolutionStrategy {
        force("androidx.core:core:1.16.0")
        // 충돌되는 지원 라이브러리 제외
        exclude(group = "com.android.support", module = "support-compat")
    }
}

android {
    namespace = "com.kkh.single.module.template"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kkh.single.module.template"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {

    dependencies {
        // Core & Compose 관련
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.desugar.jdk.libs)
        implementation(libs.androidx.navigation.compose.android)

        implementation (libs.androidx.material) // 또는 최신 버전

        //ksp
        implementation(libs.ksp.gradlePlugin)

        // Hilt 관련
        implementation(libs.hilt.android)
        implementation(libs.hilt.navigation)
        implementation(libs.hilt.navigation.compose)
        implementation(libs.hilt.ext.work)
        ksp(libs.hilt.compiler)
        testImplementation(libs.hilt.android.testing)
        androidTestImplementation(libs.hilt.android.testing)

        // Retrofit 관련
        implementation(libs.retrofit)
        implementation(libs.retrofit.kotlin.serialization)
        implementation(libs.retrofit.gson)

        // OkHttp 관련
        implementation(platform(libs.okhttp.bom))
        implementation(libs.okhttp)
        implementation(libs.okhttp.logging)

        // DataStore 관련
        implementation(libs.datastore)

        implementation(libs.androidx.work.runtime.ktx)

        implementation(libs.androidx.core.splashscreen)

        implementation(libs.coil.compose)
        implementation(libs.coil3.coil.network.okhttp)

        // 달력
        implementation(libs.material.calendarview)
        implementation(libs.threetenabp)
        implementation("com.kizitonwose.calendar:view:2.6.2")
        implementation("com.kizitonwose.calendar:compose:2.6.2")

        // 테스트 관련
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)

        // 디버그 관련
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
}