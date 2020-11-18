package pro.butovanton.weather

import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import pro.butovanton.weather.Data.DataWayCitys
import pro.butovanton.weather.Data.DataWayMain
import pro.butovanton.weather.Data.DataWayTemper
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.Domain.Factory.TestCity
import pro.butovanton.weather.Domain.Decorator.TemperatureSeason
import pro.butovanton.weather.Domain.interactor.*
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Observer.Observable

@RunWith(MockitoJUnitRunner::class)
class interactorTest {

    val city : City = TestCity()

    val dataWayMain = mock(Repo::class.java) as DataWayMain
    val observable = mock(Repo::class.java) as Observable
    val interactorMain = InteractorMain(dataWayMain, observable)
    val dataWayTemper = mock(DataWayTemper::class.java)
    val interactorTemper = InteractorTemper(dataWayTemper)
    val dataWayCitys = mock(DataWayCitys::class.java)
    val interactorCity = InteractorCitys(dataWayCitys)


    @Before
    fun setMock() {
        `when`(dataWayMain.getAll()).thenReturn(Single.just(mutableListOf(city)))
        `when`(dataWayTemper.getTemperByName(city.name)).thenReturn(Single.just(city.temperature))
        `when`(dataWayCitys.getAll()).thenReturn(Single.just(mutableListOf(city)))
        `when`(dataWayCitys.getCityByName(city.name)).thenReturn(Single.just(city))
    }

    @Test
    fun testInteractorMain() {

        interactorMain.getAll()
            .subscribe { citys ->
                assertTrue(citys[0].name.equals("test") && citys[0].type == 0)
            }
        interactorMain.getAll().subscribe { citys ->
            assertTrue(citys.size > 0)
            val temperatureSeson = TemperatureSeason()
            assertTrue(temperatureSeson.getTemperatureForSeson(city, 0) == 10.toFloat())
            assertTrue(temperatureSeson.getTemperatureForSeson(city, 1) == 20.toFloat())
            assertTrue(temperatureSeson.getTemperatureForSeson(city, 2) == 30.toFloat())
            assertTrue(temperatureSeson.getTemperatureForSeson(city, 3) == 25.toFloat())
        }

    }
    @Test
    fun testInteractorTemper() {
        interactorTemper.getTemper(city.name)
            .subscribe { temperatures ->
                assertTrue(temperatures.size == 12)
            }

        interactorTemper.setTemper(city.name, city.temperature)
        verify(dataWayTemper).saveTemperByName(city.name, city.temperature)
    }

    @Test
    fun testInteractorCitys() {
    interactorCity.getCitys()
        .subscribe { cityModels ->
            assertEquals(city.name, cityModels[0].name)
        }

    interactorCity.update(CityModel(city.name, city.type))
    verify(dataWayCitys).update(city)
    }

}