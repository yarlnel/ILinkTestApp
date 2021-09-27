package com.example.ilinktestapp.di

import com.example.ilinktestapp.network.CatApiService
import com.example.ilinktestapp.network.DuckApiService
import com.example.ilinktestapp.network.FoxApiService
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [GsonModule::class])
class RetrofitModule {
    @Singleton @Provides
    fun provideRxCallAdapterFactory(): CallAdapter.Factory
        = RxJava2CallAdapterFactory.create()


    @Provides @Singleton
    fun provideRetrofitBuilder(
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ) : Retrofit.Builder
        = Retrofit.Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)

    @Singleton @Provides @CatApiRetrofit
    fun provideCatApiRetrofit(
        retrofitBuilder: Retrofit.Builder
    ): Retrofit
        = retrofitBuilder
        .baseUrl("https://aws.random.cat/")
        .build()




    @Singleton @Provides @DuckApiRetrofit
    fun provideDuckApiRetrofit(
       retrofitBuilder: Retrofit.Builder
    ): Retrofit
        = retrofitBuilder
            .baseUrl("https://random-d.uk/api/v2/")
        .build()

    @Singleton @Provides @FoxApiRetrofit
    fun provideFoxApiRetrofit (
        retrofitBuilder: Retrofit.Builder
    ) : Retrofit
        = retrofitBuilder
            .baseUrl("https://randomfox.ca/")
            .build()



    @Singleton @Provides
    fun provideDuckApiService (
        @DuckApiRetrofit retrofit: Retrofit
    ) : DuckApiService
        = retrofit.create(DuckApiService::class.java)

    @Singleton @Provides
    fun provideCatApiService (
        @CatApiRetrofit retrofit: Retrofit
    ) : CatApiService
        = retrofit.create(CatApiService::class.java)

    @Singleton @Provides
    fun provideFoxApiService (
        @FoxApiRetrofit retrofit: Retrofit
    ) : FoxApiService
        = retrofit.create(FoxApiService::class.java)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CatApiRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DuckApiRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FoxApiRetrofit

