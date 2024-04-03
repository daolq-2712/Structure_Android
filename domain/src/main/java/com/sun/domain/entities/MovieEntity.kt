package com.sun.domain.entities

data class MovieEntity(
    val id: Int,
    val title: String,
    val description: String,
    val avatarUrl: String,
    val backgroundUrl: String,
    val rating: Double,
    val voteCount: Int,
)
