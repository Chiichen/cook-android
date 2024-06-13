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

    @Query("SELECT * FROM Recipe WHERE (stuff LIKE '%' ||:stuff||'%') OR (tools LIKE '%' || :tools || '%')")
    fun getRecipe(stuff: String, tools: String): List<Recipe>

    @Query("SELECT * FROM Recipe WHERE (stuff LIKE '%' ||:stuff||'%') AND (tools LIKE '%' || :tools || '%')")
    fun getRecipeStrictly(stuff: String, tools: String): List<Recipe>
}