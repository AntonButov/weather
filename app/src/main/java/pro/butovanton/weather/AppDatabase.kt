package pro.butovanton.weather

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Sity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): dao

}