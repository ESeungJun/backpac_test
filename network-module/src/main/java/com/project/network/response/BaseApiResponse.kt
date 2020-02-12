package com.project.network.response

import com.google.gson.annotations.SerializedName

open class BaseApiResponse {

    @SerializedName("")
    val code: Int = 0

    @SerializedName("")
    val message: String = ""
}