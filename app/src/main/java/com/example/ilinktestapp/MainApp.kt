package com.example.ilinktestapp

import android.app.Application
import android.content.Context
import com.example.ilinktestapp.di.AppComponent
import com.example.ilinktestapp.di.ContextModule
import com.example.ilinktestapp.di.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }
}

val Context.appComponent : AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this . applicationContext . appComponent
    }

