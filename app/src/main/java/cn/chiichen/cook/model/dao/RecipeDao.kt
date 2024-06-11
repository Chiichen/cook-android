package cn.chiichen.cook.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cn.chiichen.cook.model.entity.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * From Recipe")
    fun getAllRecipes(): List<Recipe>

    //TODO Deduplication
    @Query("SELECT * FROM Recipe ORDER BY RANDOM() LIMIT :num")
    fun getRandomRecipe(num: Int): List<Recipe>
}