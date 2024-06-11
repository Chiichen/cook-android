package cn.chiichen.cook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.chiichen.cook.ui.screens.AboutScreen
import cn.chiichen.cook.ui.screens.FavorPage
import cn.chiichen.cook.ui.screens.HistoryPage
import cn.chiichen.cook.ui.screens.HomeScreen
import cn.chiichen.cook.ui.screens.List.ListScreen


@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen() }
        composable("list") { ListScreen() }
        composable("about") { AboutScreen(navController) }
        composable("favor") { FavorPage(navController) }
        composable("history") { HistoryPage(navController) }
    }
}

