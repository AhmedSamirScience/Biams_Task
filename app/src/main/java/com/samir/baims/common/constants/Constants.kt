package com.samir.baims.common.constants

import com.samir.baims.BuildConfig


/**
 * Object containing constant values used throughout the application.
 */
object Constants {

    /** Base URL for API endpoints */
    val BASE_URL =BuildConfig.BASE_URL
    const val BASE_URL_EXTERNAL = BuildConfig.BASE_URL_EXTERNAL
    const val APP_ID = BuildConfig.APP_ID


    /** Tag used for logging messages related to application lifecycle */
    const val TAG_MESSAGE = "LifeCycleApplication"

    /**
     * Version name of the application.
     * If the build is in debug mode, the version name includes "rc" (release candidate) followed by the version code.
     * Otherwise, it includes "r" (release) followed by the version code.
     */
    const val VERSION_NAME = BuildConfig.VERSION_NAME

    /**
     * Secret Key used for encryption and decryption.
     */
    const val SECRET_KEY_2024 = BuildConfig.SECRET_KEY_2024

    /**
     * Network And Local
     */
    const val PREFERENCE_NAME =  "APP_SHARED_PREFERENCE"
    const val TIME_OUt : Long=  90

}
