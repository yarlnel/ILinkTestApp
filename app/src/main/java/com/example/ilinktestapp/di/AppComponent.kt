package com.example.ilinktestapp.di

import com.example.ilinktestapp.MainActivity

import com.example.ilinktestapp.ui.home.HomeFragment
import com.example.ilinktestapp.ui.home.HomeViewModel
import com.example.ilinktestapp.ui.load_images.LoadImagesFragment
import com.example.ilinktestapp.ui.load_images.LoadImagesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
 interface AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(loadImagesViewModel: LoadImagesViewModel)
    fun inject(loadImagesFragment: LoadImagesFragment)
}