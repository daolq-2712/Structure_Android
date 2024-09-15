package com.sun.data.source.remote.api.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    @Expose
    val status: Int,
    @SerializedName("messages")
    @Expose
    val messages: String?
)
