package pro.butovanton.weather

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(City::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): daoCity

}