/**
 * AppDependencies Version - 1.0
 * Date Updated - 01-March-2023
 */

const val FLAVOR_DIMENSION = "build"
const val APPLICATION_PACKAGE = "org.ifsta.hazmat5"
const val DEV_APPLICATION_PACKAGE = "dev.nickelfox.hazmat5"
const val STAGING_APPLICATION_PACKAGE = "stage.nickelfox.hazmat5"

object Plugins {
    const val AndroidApplication = "com.android.application"
    const val GoogleServices = "com.google.gms.google-services"
    const val FirebaseCrashlytics = "com.google.firebase.crashlytics"
    const val KotlinAndroid = "kotlin-android"

    const val AndroidLibrary = "com.android.library"
    const val JetBrains = "org.jetbrains.kotlin.android"
    const val Kapt = "kotlin-kapt"
    const val Hilt = "dagger.hilt.android.plugin"
    const val Parcelize = "kotlin-parcelize"
    const val NavigationSafeArgs = "androidx.navigation.safeargs.kotlin"
}

object SdkVersions {
    const val CompileSdkVersion = 33
    const val TargetSdkVersion = 33
    const val MinimumSdkVersion = 21
}

object Versions {
    const val Core = "1.9.0"
    const val AppCompat = "1.5.1"
    const val Material = "1.6.0"
    const val ConstraintLayout = "2.1.4"
    const val JUnit = "4.13.2"
    const val JUnitAndroid = "1.1.4"
    const val Espresso = "3.5.0"

    const val Glide = "4.14.2"

    const val HiltAndroid = "2.44.2"
    const val HiltWorker = "1.0.0"

    const val LifeCycle = "2.5.1"

    const val Navigation = "2.5.3"

    const val ExoPlayer = "2.18.2"

    const val DataStorePreferences = "1.0.0"

    const val MpChart = "v3.1.0"
    const val AppCenter = "4.1.0"
}

object AppLevelDependencies {

    const val AppCenter = "com.microsoft.appcenter:appcenter-crashes:${Versions.AppCenter}"

    const val Core = "androidx.core:core-ktx:${Versions.Core}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
    const val Material = "com.google.android.material:material:${Versions.Material}"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
    const val JUnitTest = "junit:junit:${Versions.JUnit}"
    const val JunitAndroidTest = "androidx.test.ext:junit:${Versions.JUnitAndroid}"
    const val Espresso = "androidx.test.espresso:espresso-core:${Versions.Espresso}"

    const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"

    const val Hilt = "com.google.dagger:hilt-android:${Versions.HiltAndroid}"
    const val HiltWorker = "androidx.hilt:hilt-work:${Versions.HiltWorker}"
    const val HiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HiltAndroid}"
    const val HiltCompiler = "androidx.hilt:hilt-compiler:${Versions.HiltWorker}"

    const val LiveDataLifeCycle = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LifeCycle}"
    const val ViewModelLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifeCycle}"

    const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation}"
    const val NavigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.Navigation}"

    const val DataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.DataStorePreferences}"
}

object AudiobookDependencies {
    const val ExoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.ExoPlayer}"
}

object ExamPrepDependencies {
    const val MpChart = "com.github.PhilJay:MPAndroidChart:${Versions.MpChart}"
}