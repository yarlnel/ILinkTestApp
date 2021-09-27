package com.example.ilinktestapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GsonModule {
    @Provides @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory
        = GsonConverterFactory.create(gson)

    @Provides @Singleton
    fun provideGson(): Gson
        = GsonBuilder().create()
}