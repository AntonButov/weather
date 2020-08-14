package pro.butovanton.weather

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import pro.butovanton.weather.Domain.Boundares
import pro.butovanton.weather.Domain.Interactor
import pro.butovanton.weather.Factory.City

@RunWith(MockitoJUnitRunner::class)
class interactorTest {

    @Test
    fun testTemper() {
       val boundaresMock = mock(Boundares::class.java)
       var temperatures = mutableListOf(10,10,20,20,20,30,30,30,25,25,25,10)
       var city = City("test", 0)
       city.temperature = temperatures.toMutableList()
       var citys = mutableListOf(city)
       `when`(boundaresMock.getAll()).thenReturn(citys)

       var interactor = Interactor(boundaresMock)

       assertTrue(interactor.getAll().size > 0)
       assertTrue(interactor.getTemper(0,0) == 10.toFloat())
       assertTrue(interactor.getTemper(0,1) == 20.toFloat())
       assertTrue(interactor.getTemper(0,2) == 30.toFloat())
       assertTrue(interactor.getTemper(0,3) == 25.toFloat())
    }

}