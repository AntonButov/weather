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
        dao.insertSity(City("test",0))
        var cities: List<City> = dao.getSitys()
        var sity = cities.get(0)
        dao.deleteAll()
        assertTrue(sity.name.equals("test"))
    }

    @Test
    fun temperForSeson() {
        var city = City("cityTest", 0)
        city.temperature =
            mutableListOf<Int?>(10, null, null, 5, 5, null, 15, null, null, 20, null, null)
        assertTrue(TemperatureSeson.getTemperatureForSeson(city = city, seson = 0) == 10.toFloat())
        assertEquals(TemperatureSeson.getTemperatureForSeson(city, 1) , 5.toFloat())
        assertEquals(TemperatureSeson.getTemperatureForSeson(city, 2) , 15.toFloat())
        assertEquals(TemperatureSeson.getTemperatureForSeson(city, 3) , 20.toFloat())
    }

}