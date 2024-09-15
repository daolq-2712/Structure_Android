plugins {
    id("kotlin")
}

dependencies {
    //Coroutine
    implementation(Deps.coroutines_core)
    implementation(Deps.coroutines_android)

    testImplementation(Deps.coroutines_test)
}
