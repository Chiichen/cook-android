package cn.chiichen.cook
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cn.chiichen.cook.navigation.BottomNavigationBar
import cn.chiichen.cook.navigation.NavigationHost

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
