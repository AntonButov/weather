package pro.butovanton.weather.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pro.butovanton.weather.Factory.City

@Database(entities = arrayOf( City::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): daoCity

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            if (INSTANCE == null)
                INSTANCE = createBd(context)

            return INSTANCE!!
        }

        fun createBd(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "sitys")
                .allowMainThreadQueries()
                .build()
        }
    }
}