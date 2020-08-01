package pro.butovanton.weather

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var db: AppDatabase
        fun getDatabase(): AppDatabase? {
            return db
        }

    }
    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(applicationContext, AppDatabase::class.java,"sitys").allowMainThreadQueries().build()
    }

}