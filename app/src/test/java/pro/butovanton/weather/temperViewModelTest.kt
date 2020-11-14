package pro.butovanton.weather

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import pro.butovanton.weather.Domain.Factory.TestCity
import pro.butovanton.weather.Domain.interactor.InteractorTemper
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Presentantion.ViewModels.MainViewModel
import pro.butovanton.weather.Presentantion.ViewModels.TemperViewModel

@RunWith(MockitoJUnitRunner::class)
class temperViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val city: City = TestCity()
    val interactor = mock(InteractorTemper::class.java)
    val model = TemperViewModel(mock(Application::class.java), interactor)


    @Before
    fun setupMock() {
        `when`(interactor.getTemper(city.name)).thenReturn(Single.just(city.temperature))
    }

    @Test
    fun test() {
        model.getCityTemperutures(city = city.name).observeForever {
            assert(it.equals(city.temperature))
        }
    }
}







