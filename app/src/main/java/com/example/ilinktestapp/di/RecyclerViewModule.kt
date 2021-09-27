package com.example.ilinktestapp.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides


@Module
class RecyclerViewModule {
    @Provides
    fun provideLinearLayoutManager
                (@AppContext appContext: Context)
        : LinearLayoutManager
            = LinearLayoutManager(appContext)
}