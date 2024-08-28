package com.samir.baims.di

import android.content.Context
import android.content.SharedPreferences
import com.samir.baims.common.constants.Constants
import com.samir.baims.data.dataSource.base.PreferencesManager
import com.samir.baims.data.dataSource.repository.LocalRepositoryImplementation
import com.samir.baims.domain.repository.dataSource.LocalRepository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    //region Data Layer
    /**
     * Data Layer Repos -> (getSharedPreferences, getPreferencesManager)
     */
    @Singleton
    @Provides
    fun getSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        /**
         * Context.MODE_PRIVATE is a mode for the SharedPreferences when you create or access them in an Android application. Here's a breakdown of what this mode does and its implications:
         *
         * Context.MODE_PRIVATE
         * When you use Context.MODE_PRIVATE in the getSharedPreferences method, it means that the SharedPreferences file you are accessing or creating will be private to your application. No other applications will be able to read or modify the data stored in this SharedPreferences file.
         *
         * ----------------------------------------------------------------------------------------------------------------------------------------------------------
         *
         * Usage
         * Here is the full line of code for reference:
         * val sharedPreferences = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
         *
         *----------------------------------------------------------------------------------------------------------------------------------------------------------
         *
         * Explanation
         * context: This is the context of the current state of the application/object. It can be an Activity, Application, or another context object.
         * Constants.PREFERENCE_NAME: This is a string constant representing the name of the SharedPreferences file. You can give it any name you prefer.
         * Context.MODE_PRIVATE: This specifies that the SharedPreferences file can only be accessed by the application that created it.
         * Example
         * val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
         *
         * // To store data
         * val editor = sharedPreferences.edit()
         * editor.putString("username", "JohnDoe")
         * editor.apply()
         *
         * // To retrieve data
         * val username = sharedPreferences.getString("username", null)
         *
         *----------------------------------------------------------------------------------------------------------------------------------------------------------
         *
         * Modes in getSharedPreferences
         * While Context.MODE_PRIVATE is the most commonly used mode, there are other modes available, though they are rarely used and have been deprecated in newer versions of Android:
         *
         * Context.MODE_WORLD_READABLE (deprecated): This mode allowed other applications to read the SharedPreferences file.
         * Context.MODE_WORLD_WRITEABLE (deprecated): This mode allowed other applications to write to the SharedPreferences file.
         * Using Context.MODE_PRIVATE ensures that your application's data is secure and isolated from other applications. This is the recommended mode for most use cases involving SharedPreferences.
         *
         * ----------------------------------------------------------------------------------------------------------------------------------------------------------
         */
        return context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
    @Singleton
    @Provides
    fun getPreferencesManager(sharedPreferences: SharedPreferences): PreferencesManager {
        return PreferencesManager(sharedPreferences)
    }
    //endregion

    //region Domain Layer
    /**
     * Domain Layer Repos
     */
    @Provides
    @Singleton
    fun provideLocalRepository(prefManager: PreferencesManager): LocalRepository {
        return LocalRepositoryImplementation(prefManager = prefManager)
    }
    //endregion


}