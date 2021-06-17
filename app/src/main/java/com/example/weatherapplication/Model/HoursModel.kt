package com.example.weatherapplication.Model

import com.google.gson.annotations.SerializedName

data class HoursModel (
    @SerializedName("rh")
    val rh: Int? = null,
    @SerializedName("weather")
    val weather : Weather,
    @SerializedName("timestamp_local")
    val timestamp_local: String? = null,
    @SerializedName("temp")
    val temp: Double? = null,
)