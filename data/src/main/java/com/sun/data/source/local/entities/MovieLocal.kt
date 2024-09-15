package com.sun.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sun.data.source.remote.model.MovieRemote
import com.sun.domain.entities.Movie

@Entity(tableName = "movies")
data class MovieLocal(
    @PrimaryKey var id: Int = -1,
    val description: String,
    val image: String,
    val backgroundUrl: String,
    val title: String,
    val rating: Double,
    val voteCount: Int
)

fun MovieLocal.toEntity(): Movie {
    return Movie(
        id = id,
        title = title,
        description = description,
        avatarUrl = image,
        backgroundUrl = backgroundUrl,
        rating = rating,
        voteCount = voteCount
    )
}

fun MovieLocal.toRemote(): MovieRemote {
    return MovieRemote(
        id = id,
        title = title,
        overView = description,
        urlImage = image,
        backDropImage = backgroundUrl,
        rating = rating,
        voteCount = voteCount
    )
}
