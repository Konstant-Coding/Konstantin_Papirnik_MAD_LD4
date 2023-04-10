package com.example.movieappmad23.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies


class ViewModel: ViewModel() {
    var movies = getMovies().toMutableStateList()
    val allMovies: List<Movie>
        get() = movies

    var moviesfavourite = mutableListOf<Movie>().toMutableStateList()
    val favoriteMovies: List<Movie>
        get() = moviesfavourite

    var addMovie: Movie = Movie("", "", "", listOf(), "", "", "", listOf(), 0.0f)

    var isDisabled: MutableState<Boolean> = mutableStateOf(false)

    var title = mutableStateOf(addMovie.title)
    var errorTitle: MutableState<Boolean> = mutableStateOf(false)

    val year = mutableStateOf(addMovie.year)
    var errorYear: MutableState<Boolean> = mutableStateOf(false)

    var actors = mutableStateOf(addMovie.actors)
    var errorActors: MutableState<Boolean> = mutableStateOf(false)

    var plot = mutableStateOf(addMovie.plot)
    var errorPlot: MutableState<Boolean> = mutableStateOf(false)

    var director = mutableStateOf(addMovie.director)
    var errorDirector: MutableState<Boolean> = mutableStateOf(false)

    var genreItems = mutableStateOf(
        Genre.values().map { genre ->
            ListItemSelectable(
                title = genre.toString(),
                isSelected = false
            )
        }
    )
    var genreError: MutableState<Boolean> = mutableStateOf(false)

    var rating = mutableStateOf(addMovie.rating.toString().replace("0.0", ""))
    var errorRating: MutableState<Boolean> = mutableStateOf(false)

    fun init() {
        validate("title")
        validate("year")
        validate("genres")
        validate("actors")
        validate("director")
        validate("plot")
        validate("rating")
    }

    fun addMovie(
        title: String,
        year: String,
        genres: List<Genre>,
        actors: String,
        director: String,
        plot: String,
        rating: String
    ) {
        val newMovie = Movie(
            id = "$title + $year + $director+ $genres + $actors",
            title = title,
            year = year,
            genre = genres,
            actors = actors,
            director = director,
            plot = plot,
            rating = rating.toFloat(), images = listOf(
                "https://i.ds.at/rc9FAA/rs:fill:750:0/plain/2014/01/14/1388693526907-wallstreet-800.jpg",
                "https://www.apple.com/newsroom/images/product/app-store/app-store-awards/Apple_App-Store-Awards-2021_hero_12022021_big.jpg.large.jpg",)
        )
        movies.add(newMovie)
    }

    fun validate(field: String) {
        when (field) {
            //"title" -> if (title.value.isEmpty()) //tried to have only error, if already been in field
            "title" -> errorTitle.value = title.value.isEmpty()
            "year" -> errorYear.value = year.value.isEmpty()
            "genres" -> {
                genreError.value = true
                genreItems.value.forEach genres@{
                    if (it.isSelected) {
                        genreError.value = false
                        return@genres
                    }
                }
                enable()
            }
            "actors" -> errorActors.value = actors.value.isEmpty()
            "director" -> errorDirector.value = director.value.isEmpty()
            "plot" -> errorPlot.value = plot.value.isEmpty()
            "rating" -> {
                try {
                    rating.value.toFloat()
                    errorRating.value = false
                } catch (e: java.lang.Exception) {
                    errorRating.value = true
                } finally {
                    enable()
                }
            }

        }
        enable()
    }

    private fun enable() {
        isDisabled.value =
            (errorTitle.value.not()
                    && errorYear.value.not()
                    && errorActors.value.not()
                    && errorDirector.value.not()
                    && errorPlot.value.not()
                    && errorRating.value.not()
                    && genreError.value.not()
                    )
    }

    fun toggleFavorite(movie: Movie) {
        movies.find { it.id == movie.id }?.let { task ->
            task.isFavorite = !task.isFavorite
            if (task.isFavorite) {
                moviesfavourite.add(movie)

            } else {
                moviesfavourite.remove(movie)
            }
        }
    }
}