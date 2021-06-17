package com.example.weatherapplication.Model

import com.google.gson.annotations.SerializedName

data class DaysModel(
    @SerializedName("weather")
    val weather : Weather,
    @SerializedName("datetime")
    val datetime: String? = null,
    @SerializedName("low_temp")
    val low_temp: Double? = null,
    @SerializedName("max_temp")
    val max_temp: Double? = null
)