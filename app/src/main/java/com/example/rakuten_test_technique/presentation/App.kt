package com.example.rakuten_test_technique.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Class [App] overload of class [Application].
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Timber creation for logs
        Timber.plant(DebugTree())
    }
}