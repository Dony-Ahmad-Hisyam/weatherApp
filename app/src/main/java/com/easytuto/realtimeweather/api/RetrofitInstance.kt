package com.easytuto.realtimeweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private const val baseUrl = "https://api.weatherapi.com"

    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherModel : WeatherApi = getInstance().create(WeatherApi::class.java)

}