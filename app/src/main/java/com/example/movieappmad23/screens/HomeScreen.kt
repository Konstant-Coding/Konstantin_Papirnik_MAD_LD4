package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies
import com.example.movieappmad23.widgets.HomeTopAppBar
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.ViewModel.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: ViewModel = viewModel()){
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.AddMovieScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Add Movie", modifier = Modifier.padding(5.dp))
                        Text(text = "Add Movie", modifier = Modifier
                            .width(100.dp)
                            .padding(5.dp))
                    }
                }
                DropdownMenuItem(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(5.dp))
                        Text(text = "Favorites", modifier = Modifier
                            .width(100.dp)
                            .padding(5.dp))
                    }
                }
            }
        )
    }) { padding ->
        MainContentA(modifier = Modifier.padding(padding), navController = navController, viewModel = viewModel)
    }

}

@Composable
fun MainContentA(
    modifier: Modifier,
    navController: NavController,
    viewModel: ViewModel = ViewModel()
) {
    MovieList(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel,
    )
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ViewModel,
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(viewModel.allMovies) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(Screen.DetailScreen.withId(movieId))
                },
                onFavClick = { movieId ->
                    viewModel.toggleFavorite(movieId)
                }
            )
        }
    }
}
