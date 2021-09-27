package com.example.ilinktestapp.network

import com.example.ilinktestapp.models.DuckResponse
import io.reactivex.Single
import retrofit2.http.GET


interface DuckApiService {
    @GET("random")
    fun getRandomDuck () : Single<DuckResponse>
}