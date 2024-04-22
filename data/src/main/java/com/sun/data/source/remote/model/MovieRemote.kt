package com.sun.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sun.data.source.local.entities.MovieLocal
import com.sun.domain.entities.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieRemote(
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
    var rating: Double = 0.0,
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

fun MovieRemote.toEntity(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overView,
        avatarUrl = urlImage,
        backgroundUrl = backDropImage,
        rating = rating,
        voteCount = voteCount
    )
}

fun MovieRemote.toLocal(): MovieLocal {
    return MovieLocal(
        id = id,
        description = overView,
        image = urlImage,
        backgroundUrl = backDropImage,
        title = title,
        rating = rating,
        voteCount = voteCount
    )
}
