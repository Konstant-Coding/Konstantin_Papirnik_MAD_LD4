package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.ViewModel.ViewModel
import com.example.movieappmad23.screens.*

@Composable
fun Navigation(viewModel: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, viewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, viewModel)
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, viewModel)
        }

        /* Tried to add function, that when adding movie, app travels back to homescreen, but monkey brain
           coulnd't compute anymore, sorry
        composable(Screen.AddMovieButton.route) {
            AddMovieScreen(navController = navController, viewModel)
        }
        */

        // build a route like: root/detail-screen/id=34
        composable(Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController, viewModel = viewModel, movieId = backStackEntry.arguments?.getString(
                DETAIL_ARGUMENT_KEY)
            )   // get the argument from navhost that will be passed
        }
    }
}