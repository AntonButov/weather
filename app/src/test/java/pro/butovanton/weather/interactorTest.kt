package pro.butovanton.weather

import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import pro.butovanton.weather.Domain.Boundares
import pro.butovanton.weather.Domain.Interactor
import pro.butovanton.weather.Domain.TemperatureSeson
import pro.butovanton.weather.Factory.City

@RunWith(MockitoJUnitRunner::class)
class interactorTest {

    @Test
    fun testTemper() {
       val boundaresMock = mock(Boundares::class.java)
       var temperatures = mutableListOf(10,10,20,20,20,30,30,30,25,25,25,10)
       var city = City("test", 0)
       city.temperature = temperatures.toMutableList()
       var citys = Flowable.fromArray(mutableListOf(city))
       `when`(boundaresMock.getAll()).thenReturn(citys)


       var interactor = Interactor(boundaresMock)

       interactor.getAll().subscribe { citys ->
           assertTrue(citys.size > 0)
           var city = citys[0]
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,0) == 10.toFloat())
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,1) == 20.toFloat())
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,2) == 30.toFloat())
           assertTrue(TemperatureSeson.getTemperatureForSeson(city,3) == 25.toFloat())
       }
    }

}