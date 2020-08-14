package pro.butovanton.weather

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.Domain.TemperatureSeson

@RunWith(AndroidJUnit4::class)
class bdTests {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    var aldCity : MutableList<City>? = null
    val db = InjectorUtils.provideDb(appContext)
    val dao = db.getDao()

    @Before
    fun before() {
        aldCity = dao.getSitys()
        dao.deleteAll()
    }

    @After
    fun after() {
        dao.deleteAll()
        for (city : City in aldCity!!)
            dao.insertSity(city)
    }

    @Test
    fun dbTestNew() {
        dao.insertSity(City("test", 0))
        var cities: List<City> = dao.getSitys()
        var sity = cities.get(0)
        assertTrue(sity.name.equals("test"))
    }

}