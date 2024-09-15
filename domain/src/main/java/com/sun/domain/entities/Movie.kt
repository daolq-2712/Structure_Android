package com.sun.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val avatarUrl: String,
    val backgroundUrl: String,
    val rating: Double,
    val voteCount: Int,
)
