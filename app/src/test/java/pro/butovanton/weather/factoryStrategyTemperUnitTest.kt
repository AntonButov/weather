package pro.butovanton.weather

import org.junit.Test

import org.junit.Assert.*
import pro.butovanton.weather.Presentantion.Strategy.Strategy
import pro.butovanton.weather.Domain.Decorator.TemperatureSeason
import pro.butovanton.weather.Factory.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class factoryStrategyTemperUnitTest {

    val temperatureSeson = TemperatureSeason()

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
        assertTrue(temperatureSeson.sesons.size == 4)
        assertTrue(temperatureSeson.autumn.size == 3)
        assertTrue(temperatureSeson.summer.size == 3)
        assertTrue(temperatureSeson.spring.size == 3)
        assertTrue(temperatureSeson.winter.size == 3)
        var months : Int = 0
        for (seson: Int in 0 .. 3)
            months += temperatureSeson.sesons[seson].size
        assertTrue(months == 12)
    }

    @Test
    fun temperForSeson() {
        var city = City("cityTest", 0)
        city.temperature =
            mutableListOf<Int?>(10, null, null, 5, 5, null, 15, null, null, 20, null, null)
        assertEquals(temperatureSeson.getTemperatureForSeson(city,  0) , 10.toFloat())
        assertEquals(temperatureSeson.getTemperatureForSeson(city, 1) , 5.toFloat())
        assertEquals(temperatureSeson.getTemperatureForSeson(city, 2) , 15.toFloat())
        assertEquals(temperatureSeson.getTemperatureForSeson(city, 3) , 20.toFloat())
    }

    @Test
    fun strategy() {
        assertEquals(10.toFloat(), Strategy.calculate(0, 10.toFloat()))
        assertEquals(104.toFloat(), Strategy.calculate(1, 40.toFloat()))
        assertEquals(283.15.toFloat(), Strategy.calculate(2, 10.toFloat()))
    }
}