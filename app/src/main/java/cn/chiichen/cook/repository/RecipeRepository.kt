package cn.chiichen.cook.repository

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.LiveData
import cn.chiichen.cook.model.dao.RecipeDao
import cn.chiichen.cook.model.entity.Recipe
import cn.chiichen.cook.utils.CsvReader
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

const val RECIPE_ASSET_PATH = "data.csv"

class RecipeRepository(private val assetManager: AssetManager, val recipeDao: RecipeDao) {
    suspend fun initCsvData() {
        coroutineScope {
            async {
                val recipeData: ArrayList<Recipe> =
                    CsvReader.readCsvFromAsset(assetManager, RECIPE_ASSET_PATH);
                Log.i("RecipeRepo", String.format("Read Csv data with length %s", recipeData.size))
                recipeDao.insertRecipes(recipeData)
            }.await()

        }
    }

    fun getRandomRecipe(count: Int): LiveData<List<Recipe>> {
        Log.i("RecipeRepo", String.format("Random %d recipe", count))
        return recipeDao.getRandomRow(count)
    }
}