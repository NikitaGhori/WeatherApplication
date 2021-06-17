package com.example.weatherapplication

import com.example.weatherapplication.Model.HoursModel
import com.google.gson.annotations.SerializedName

data class HoursDataResp(
    @SerializedName("data")
    val `data`: List<HoursModel>? = null,
    @SerializedName("city_name")
    val city_name: String? = null,
    @SerializedName("lon")
    val lon: Double? = null,
    @SerializedName("timezone")
    val timezone: String? = null,
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("country_code")
    val country_code: String? = null,
    @SerializedName("state_code")
    val state_code: Int? = null
)