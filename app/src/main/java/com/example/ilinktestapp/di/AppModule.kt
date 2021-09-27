package com.example.ilinktestapp.di

import androidx.recyclerview.widget.RecyclerView
import dagger.Module

@Module(includes =
    [RetrofitModule::class,
    GsonModule::class,
    ContextModule::class,
    PicassoModule::class,
    DatabaseModule::class,
    RecyclerViewModule::class])
class AppModule {
}

