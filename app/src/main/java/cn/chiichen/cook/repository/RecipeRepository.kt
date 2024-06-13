package cn.chiichen.cook.repository

import android.content.res.AssetManager
import android.util.Log
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
                for (it in recipeData) {
                    Log.i("RecipeRepo", String.format("Inserting  recipe %s", it))
                    recipeDao.insertRecipe(it)
                }
            }.await()

        }
    }

    fun getRandomRecipe(count: Int): List<Recipe> {
        Log.i("RecipeRepo", String.format("Random %d recipe", count))
        return recipeDao.getRandomRecipe(count)
    }

    fun getAllRecipe(): List<Recipe> {
        Log.i("RecipeRepo", String.format("Get All recipe"))
        return recipeDao.getAllRecipes()
    }

    fun getRecipe(stuffs: List<String>, tools: List<String>): List<Recipe> {
        Log.i("RecipeRepo", String.format("Get recipe stuff:%s tools%s", stuffs, tools))
        val res: MutableSet<Recipe> = mutableSetOf()
        for (stuff in stuffs) {
            for (tool in tools) {
                for (recipe in recipeDao.getRecipe(stuff, tool)) {
                    res.add(recipe)
                }
            }
        }
        return res.toList()
    }

    fun getRecipeStrictly(stuffs: List<String>, tools: List<String>): List<Recipe> {
        Log.i("RecipeRepo", String.format("Get recipe strictly stuff:%s tools%s", stuffs, tools))
        val res: MutableSet<Recipe> = mutableSetOf()
        for (stuff in stuffs) {
            for (tool in tools) {
                for (recipe in recipeDao.getRecipeStrictly(stuff, tool)) {
                    res.add(recipe)
                }
            }
        }
        return res.toList()
    }
}