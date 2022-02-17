plugins {
    id(Plugins.android_application)
    kotlin(Plugins.kotlin_android)
    id(Plugins.detekt).version(Versions.detekt)
}

buildscript {
    apply(from = "ktlint.gradle.kts")
}

android {
    namespace = "com.sun.structure_android"
    compileSdk = AppConfigs.compile_sdk_version

    defaultConfig {
        applicationId = AppConfigs.application_id
        minSdk = AppConfigs.min_sdk_version
        targetSdk = AppConfigs.target_sdk_version
        versionCode = AppConfigs.version_code
        versionName = AppConfigs.version_name
    }

    @Suppress("UnstableApiUsage")
    flavorDimensions += listOf("appVariant")

    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Structure-Dev")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
        create("prd") {
            resValue("string", "app_name", "Structure")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            versionCode = AppConfigs.version_code_release
            versionName = AppConfigs.version_name_release
        }
    }

    @Suppress("UnstableApiUsage")
    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        getByName("release") {
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            setProguardFiles(
                setOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
                )
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
        viewBinding = true
    }
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom("$rootDir/config/detekt/detekt.yml") // config rules file
    source.setFrom("src/main/java")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
        md.required.set(true) // simple Markdown format
    }
}

tasks {
    check {
        dependsOn("ktlintCheck")
        dependsOn("ktlintFormat")
    }
}

dependencies {
    implementation(Deps.core_ktx)
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation(Deps.constraint_layout)

    //Navigation
    implementation(Deps.navigation_fragment)
    implementation(Deps.navigation_ui)
    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_ui_ktx)

    //Lifecycle
    implementation(Deps.lifecycle_extension)
    implementation(Deps.lifecycle_livedata_ktx)
    implementation(Deps.lifecycle_viewmodel_ktx)
    implementation(Deps.lifecycle_runtime)

    //Coroutine
    implementation(Deps.coroutines_core)
    implementation(Deps.coroutines_android)
    testImplementation(Deps.coroutines_test)

    //Retrofit
    implementation(Deps.okHttp)
    implementation(Deps.retrofit_runtime)
    implementation(Deps.retrofit_gson)
    implementation(Deps.okhttp_logging_interceptor)

    //Koin
    implementation(Deps.koin_ext)
    implementation(Deps.koin_viewmodel)

    //Glide
    implementation(Deps.glide_runtime)
    implementation(Deps.glide_compiler)

    //Test
    testImplementation(Deps.junit)
    testImplementation(Deps.mockk)
}
