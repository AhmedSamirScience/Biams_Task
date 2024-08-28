object ProjectPlugins {
    //plugin to build Android app (e.g. default app module)
    const val ANDROID_APP = "com.android.application"
    const val ANDROID_APP_VERSION_NUMBER = "8.4.2"

    //plugin to enable Kotlin support in your project.
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_ANDROID_PLUGIN_VERSION_NUMBER = "2.0.0"


    //region plugin to enable safeArgs in your project.
    const val SAFE_ARGS_PROJECT_MODULE = "androidx.navigation.safeargs.kotlin"
    const val SAFE_ARGS_APP_MODULE = "androidx.navigation.safeargs"
    const val SAFE_ARGS_APP_MODULE_VERSION_NUMBER = "2.7.7"
    //endregion

    // region plugin to enable kotlin Parcelize
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
    //endregion

    // region Dagger Hilt
    const val DAGGER_HILT = "com.google.dagger.hilt.android"
    const val DAGGER_HILT_VERSION_NUMBER = "2.49"

    const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val DAGGER_HILT_PLUGIN = "dagger.hilt.android.plugin"
    //endregion
}