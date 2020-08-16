package pro.butovanton.weather

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.Flowable
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
        dao.getSitys()
            .subscribe {aldCity -> this.aldCity = aldCity
        dao.deleteAll() }
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
        var result = mutableListOf<City>()
        dao.getSitys()
            .subscribe { c -> result = c }
        assertTrue(result[0].name.equals("test"))
    }

}