package com.example.weatherapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
    @GET("hourly")
    fun getHoursData(
        @Query("city") city: String,
        @Query("key") key: String,
        @Query("hours") hours: Int
    ): Call<HoursDataResp?>


    @GET("daily")
    fun getDaysData(
        @Query("city") city: String,
        @Query("days") days: Int,
        @Query("key") key: String, ): Call<DaysDataResp?>

    companion object {

    const val BASE_URL = "https://api.weatherbit.io/v2.0/forecast/"
}
}

