package pro.butovanton.weather

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import pro.butovanton.weather.Factory.City

@RunWith(AndroidJUnit4::class)
class bdTests {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    var aldCity : MutableList<City>? = null
    val db = InjectorUtils.provideDb(appContext)
    val dao = db.getDao()

    var city = initTestCity()

    fun initTestCity(): City {
        val city = City("test", 0)
        city.temperature[0] = 10
        return city
    }


    @Before
    fun before() {
        dao.getCitys()
            .subscribe { aldCity -> this.aldCity = aldCity
        dao.deleteAll() }
    }

    @After
    fun after() {
        dao.deleteAll()
        for (city : City in aldCity!!)
            dao.insertCity(city)
    }

    @Test
    fun dbTestNew() {
        dao.insertCity(city)
        var result = mutableListOf<City>()
        dao.getCitys()
            .subscribe { c -> result = c }
        assertTrue(result[0].name.equals("test"))
        assertTrue(result[0].temperature[0] == 10)
    }

    @Test
    fun dbTestGetCityByName() {
        var result = City("",0)
        dao.insertCity(city)
        dao.getCityByName(city.name)
            .subscribe{
                cityByName -> result = cityByName }
        assertTrue(result.name.equals(city.name))
    }

    @Test
    fun dbTestGetTemperByName() {
        var result = mutableListOf<Int?>()
        dao.insertCity(city)
        dao.getCityByName(city.name)
            .subscribe{
                    city -> result = city.temperature }
        assertTrue(result[0] == 10)
    }
}