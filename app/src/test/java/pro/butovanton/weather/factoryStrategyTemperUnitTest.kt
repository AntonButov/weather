package pro.butovanton.weather

import org.junit.Test

import org.junit.Assert.*
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.Domain.TemperatureSeson
import pro.butovanton.weather.Factory.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class factoryStrategyTemperUnitTest {
    @Test
    fun factoryTest() {
        var city: City
        val factory = Factory()
        val name = "Test"

        city = factory.Creat(0, name)
        assertTrue(city is Small)

        city = factory.Creat(1, name)
        assertTrue(city is Medium)

        city = factory.Creat(2, name)
        assertTrue(city is Big)
    }

    @Test
    fun testSesons() {
        assertTrue(TemperatureSeson.sesons.size == 4)
        assertTrue(TemperatureSeson.autumn.size == 3)
        assertTrue(TemperatureSeson.summer.size == 3)
        assertTrue(TemperatureSeson.spring.size == 3)
        assertTrue(TemperatureSeson.winter.size == 3)
        var months : Int = 0
        for (seson: Int in 0 .. 3)
            months += TemperatureSeson.sesons[seson].size
        assertTrue(months == 12)
    }

    @Test
    fun temperForSeson() {
        var city = City("cityTest", 0)
        city.temperature =
            mutableListOf<Int?>(10, null, null, 5, 5, null, 15, null, null, 20, null, null)
        assertEquals(TemperatureSeson.getTemperatureForSeson(city,  0) , 10.toFloat())
        assertEquals(TemperatureSeson.getTemperatureForSeson(city, 1) , 5.toFloat())
        assertEquals(TemperatureSeson.getTemperatureForSeson(city, 2) , 15.toFloat())
        assertEquals(TemperatureSeson.getTemperatureForSeson(city, 3) , 20.toFloat())
    }

    @Test
    fun strategy() {
        assertEquals(10.toFloat(), Strategy.calculate(0, 10.toFloat()))
        assertEquals(104.toFloat(), Strategy.calculate(1, 40.toFloat()))
        assertEquals(283.15.toFloat(), Strategy.calculate(2, 10.toFloat()))
    }
}