package com.sun.android.data.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status")
    @Expose
    val status: Int,
    @SerializedName("messages")
    @Expose
    val message: String,
    @SerializedName("data")
    @Expose
    var data: T
)
