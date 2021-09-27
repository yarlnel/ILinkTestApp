package com.example.ilinktestapp.di

import android.content.Context
import androidx.room.Room
import com.example.ilinktestapp.db.ImageDao
import com.example.ilinktestapp.db.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides @Singleton @DatabaseName
    fun provideDBName() : String
        = "main_db"

    @Provides @Singleton
    fun provideMainDB(
        @AppContext appContext: Context,
        @DatabaseName name: String
    ) : MainDatabase
        = Room.databaseBuilder(
            appContext,
            MainDatabase::class.java,
            name
        )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides @Singleton
    fun provideImageDao (
        mainDatabase: MainDatabase
    ) : ImageDao
        = mainDatabase.imageDao()

}
@Qualifier annotation class DatabaseName