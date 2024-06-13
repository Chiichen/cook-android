package cn.chiichen.cook.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cn.chiichen.cook.model.dao.RecipeDao
import cn.chiichen.cook.model.entity.Recipe

@Database(entities = [Recipe::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}