package com.sun.android.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    @Expose
    var id: Int = -1,
    @SerializedName("backdrop_path")
    @Expose
    var backDropImage: String = "",
    @SerializedName("overview")
    @Expose
    var overView: String = "",
    @SerializedName("vote_average")
    @Expose
    var vote: Double = 0.0,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0,
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("poster_path")
    @Expose
    var urlImage: String = "",
    @SerializedName("original_title")
    @Expose
    var originalTitle: String = ""
) : Parcelable
