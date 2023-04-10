package com.example.movieappmad23.models

data class Data (
    var title: String = "",
    var plot: String = "",
    var genres: List<Genre> = listOf(),
    var year: String = "",
    var actors: String = "",
    var rating: Float = 0.0f,
    var director: String = "",
)