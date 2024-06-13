package cn.chiichen.cook


import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cn.chiichen.cook.database.DatabaseManager
import cn.chiichen.cook.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val db = DatabaseManager.getAppDatabase(this)
        val recipeDao = db.recipeDao()
        val recipeRepository = RecipeRepository(this.assets, recipeDao)
        MainScope().launch(Dispatchers.IO) {
            Log.i("MainActivity", "init csv data in list screen")
            recipeRepository.initCsvData();
        }
        setContent {
            MyApp()
        }
    }
}
