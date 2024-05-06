package com.addas.algovisualizer.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.addas.algovisualizer.MainScreen
import com.addas.algovisualizer.ui.selection_sort.SelectionSortScreen
import com.addas.algovisualizer.ui.selection_sort.SelectionSortViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.SelectionSortScreen.route) {
            SelectionSortScreen(viewModel = hiltViewModel<SelectionSortViewModel>().apply { this.generateArray() })
        }
    }

}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object SelectionSortScreen : Screen("selection_sort")
}