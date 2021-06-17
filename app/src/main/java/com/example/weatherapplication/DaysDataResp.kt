package com.example.weatherapplication

import com.example.weatherapplication.Model.DaysModel
import com.google.gson.annotations.SerializedName

data class DaysDataResp(
    @SerializedName("data")
    val `data`: List<DaysModel>? = null,
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