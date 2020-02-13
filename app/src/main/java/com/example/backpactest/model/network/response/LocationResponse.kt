package com.example.backpactest.model.network.response

import com.google.gson.annotations.SerializedName
import com.project.network.response.BaseApiResponse

data class LocationResponse (
    @SerializedName("title")
    var title: String = "",

    @SerializedName("location_type")
    var locationType: String = "",

    @SerializedName("woeid")
    var woeid: Int = 0,

    @SerializedName("latt_long")
    var lattAndlong: String = ""

) : BaseApiResponse()
