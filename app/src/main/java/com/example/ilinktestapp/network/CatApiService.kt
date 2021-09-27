package com.example.ilinktestapp.network

import com.example.ilinktestapp.models.CatResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CatApiService {
    @GET("meow")
    fun getRandomCat () : Single<CatResponse>
}