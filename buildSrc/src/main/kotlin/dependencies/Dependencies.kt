package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    //region default
    const val ANDROID_CORE = "androidx.core:core-ktx:${Versions.ANDROID_CORE}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val TEST_EXT = "androidx.test.ext:junit:${Versions.TEST_EXT}"
    const val TEST_ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.TEST_ESPRESSO}"
    //endregion

    //region Navigation Graph for Kotlin language implementation
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAV_VERSION}"
    const val NAVIGATION_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAV_VERSION}"
    //endregion

    //region viewModel dependencies
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.LEGACY_SUPPORT}"
    const val LIFECYCLE_LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVE_DATA}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEWMODEL}"
    //const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
    //endregion

    //region design margins
    const val INTUIT_SDB = "com.intuit.sdp:sdp-android:${Versions.DESIGN_MARGINS}"
    const val INTUIT_SSP = "com.intuit.ssp:ssp-android:${Versions.DESIGN_MARGINS}"
    //endregion

    // region gif drawable
    const val GIF_DRAWABLE = "pl.droidsonroids.gif:android-gif-drawable:${Versions.GIF_DRAWABLE}"
    //endregion

    // region gif lifecycleRuntimeKtx
    /**
     * this is to access life cycle scope
     */
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME_KTX}"
    //endregion

    //region dagger Hilt
    const val DAGGER_HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.DAGGER_HILT_ANDROID}"
    //const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE_VIEWMODEL}"

    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.DAGGER_HILT_COMPILER}"
    const val ANDROID_HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.ANDROID_HILT_COMPILER}"
    //endregion

    //region Coroutines
    const val KOTLINX_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINE_CORE}"
    const val KOTLINX_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES_ANDROID}"
    //endregion

    //region retrofit
    const val RETROFIT_SQUARE_UP = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_SQUARE_UP}"
    const val RETROFIT_SQUARE_UP_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_SQUARE_UP_CONVERTER_GSON}"
    const val OKHTTP_SQUARE_UP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP_SQUARE_SQUARE_UP}"
    const val INTERCEPTOR_SQUARE_UP = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_SQUARE_SQUARE_UP}"
    //endregion
}

fun DependencyHandler.defaultLibraries() {
    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.TEST_EXT)
    androidTestImplementation(Dependencies.TEST_ESPRESSO)
}

fun DependencyHandler.navGraph() {
    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_KTX)
}

fun DependencyHandler.viewModel() {
    implementation(Dependencies.LEGACY_SUPPORT)
    implementation(Dependencies.LIFECYCLE_LIVE_DATA)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    //implementation(Dependencies.lifecycleViewModelCompose)
}

fun DependencyHandler.designMargins() {
    implementation(Dependencies.INTUIT_SDB)
    implementation(Dependencies.INTUIT_SSP)
}

fun DependencyHandler.gifDrawable() {
    implementation(Dependencies.GIF_DRAWABLE)
}

fun DependencyHandler.lifecycleRuntimeKtx() {
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.DAGGER_HILT_ANDROID)
    //  implementation(Dependencies.hiltLifeCycleViewModel)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    kapt(Dependencies.ANDROID_HILT_COMPILER)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.KOTLINX_COROUTINES_CORE)
    implementation(Dependencies.KOTLINX_COROUTINES_ANDROID)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT_SQUARE_UP)
    implementation(Dependencies.RETROFIT_SQUARE_UP_CONVERTER_GSON)
    implementation(Dependencies.OKHTTP_SQUARE_UP)
    implementation(Dependencies.INTERCEPTOR_SQUARE_UP)
}