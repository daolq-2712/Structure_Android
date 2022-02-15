
object Versions {

    const val gradlePlugin = "7.0.4"
    const val kotlin = "1.6.10"

    const val coreKtx = "1.6.0"
    const val appCompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.0"
    const val coreSplashScreen = "1.0.0-beta01"

    const val lifecycle = "2.3.1"
    const val navigation = "2.3.5"
    const val koin = "3.1.2"

    const val jUnit = "4.13.2"
    const val mockk = "1.12.0"
}

object AppConfigs {
    const val application_id = "com.sun.structure_android"
    const val compile_sdk_version = 31
    const val min_sdk_version = 23
    const val target_sdk_version = 30 // improve later
    const val version_code = 1
    const val version_name = "1.0"
}

object ClassPath {
    const val gradle_build_tools = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Plugin {
    const val android_application = "com.android.application"
    const val kotlin_android = "android"
}

object Deps {
    const val core_ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Splash screen
    const val splash_screen = "androidx.core:core-splashscreen:${Versions.coreSplashScreen}"

    // lifecycle
    const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // navigation
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // koin
    const val koin_core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin}"
    const val koin_test = "io.insert-koin:koin-test:${Versions.koin}"

    // Testing
    const val junit = "junit:junit:${Versions.jUnit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}