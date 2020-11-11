package pro.butovanton.weather

import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import pro.butovanton.weather.Domain.Boundares
import pro.butovanton.weather.Domain.Interactor
import pro.butovanton.weather.Domain.TemperatureSeson
import pro.butovanton.weather.Factory.City

@RunWith(MockitoJUnitRunner::class)
class interactorTest {

    val city = City("test", 0)
    val temperatures = mutableListOf<Int?>(10,10,20,20,20,30,30,30,25,25,25,10)
    val citys : Single<MutableList<City>> = initSingle()
    val boundaresMock = mock(Boundares::class.java)
    val interactor = Interactor(boundaresMock)

    fun initSingle(): Single<MutableList<City>> {
        city.temperature = temperatures
    return Single.just(mutableListOf(city))
    }

    @Before
    fun setMock() {
        `when`(boundaresMock.getAll()).thenReturn(citys)
        `when`(boundaresMock.getTemperByName(city.name)).thenReturn(Single.just(temperatures))
    }

    @Test
    fun testTemper() {
       interactor.getAll().subscribe { citys ->
           assertTrue(citys.size > 0)
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,0) == 10.toFloat())
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,1) == 20.toFloat())
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,2) == 30.toFloat())
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,3) == 25.toFloat())

       interactor.getTemper(city.name)
           .subscribe { temperatures ->
               assertTrue( temperatures.size == 12)
           }

       interactor.getCitys()
           .subscribe { citys ->
               assertTrue(citys[0].name.equals("test") && citys[0].type == 0)
           }
       }
    }

}