package cn.chiichen.cook.database

import android.content.Context
import androidx.room.Room

object DatabaseManager {


    private var INSTANCE: AppDatabase? = null //创建单例

    fun getAppDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE!!;
    }
}