package com.example.ilinktestapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class ContextModule(private val _appContext : Context) {
    @Singleton @Provides @AppContext
    fun provideAppContext() : Context
        = _appContext
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext