package pro.butovanton.weather

import android.app.Application
import androidx.room.Room
import pro.butovanton.weather.Data.AppDatabase
import pro.butovanton.weather.Data.Repo

class App : Application() {

  lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(applicationContext, AppDatabase::class.java,"sitys")
                .allowMainThreadQueries()
                .build()
        val dao = db.getDao()
        Repo.get(dao)
    }

}