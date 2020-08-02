package pro.butovanton.weather

import android.app.Application
import androidx.room.Room

class App : Application() {

  lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(applicationContext, AppDatabase::class.java,"sitys")
                .allowMainThreadQueries()
                .build()
        val dao = db.getDao()
        Repo.instance.setDao(dao)
    }

}