package pro.butovanton.weather

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun dbTest() {
        val db = Room.databaseBuilder(appContext, AppDatabase::class.java,"sitys").allowMainThreadQueries().build()
        val dao = db.getDao()
        dao.insertSity(City("test"))
        var cities: List<City> = dao.getSitys()
        var sity = cities.get(0)
        dao.deleteAll()
        assertTrue(sity.name.equals("test"))
    }

}