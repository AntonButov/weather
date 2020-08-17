package pro.butovanton.weather

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import pro.butovanton.weather.Domain.Boundares
import pro.butovanton.weather.Domain.Interactor
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.ViewModels.MainViewModel

@RunWith(MockitoJUnitRunner::class)
class mainViewModelTest {

    @Test
    fun testTemper() {
        /*
    var appContectMock = mock(Application::class.java)
    var modelMock = MainViewModel(appContectMock)

       var cityNames = MutableLiveData<List<String>>()
        `when`(modelMock.getCitysNames()).thenReturn(cityNames)

        var city = City("test", 0)

        var citys = Flowable.fromArray(mutableListOf(city))
        `when`(modelMock.getDataFromBD()).thenReturn(citys)
        modelMock.getCitysNames().observeForever(Observer { seson ->
            seson
        })
        cityNames.value = mutableListOf("test")

        modelMock.getCitysNames().observeForever(Observer { cityNames ->
           assertTrue(cityNames.size > 0)
        })

         */
       }
    }


