package org.wit.placemark.main

import android.app.Application
import org.wit.placemark.models.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.ProfileJSONStore
import org.wit.placemark.models.ProfileStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore
    lateinit var profile: ProfileStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        placemarks = PlacemarkJSONStore(applicationContext)
        profile = ProfileJSONStore(applicationContext)
        i("HYAH started")
    }
}