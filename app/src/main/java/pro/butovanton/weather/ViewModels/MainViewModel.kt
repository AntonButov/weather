package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.InjectorUtils

class MainViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var citys : List<City>
    var city : Int = 0
    var seson : Int = 0
    var strategy : Int = 0

    val interactor = InjectorUtils.provideInteractor(application)
    val cityNames: MutableList<String> = mutableListOf()

    fun getCitysNames() : List<String> {
        citys = interactor.getAll()
        cityNames.clear()
        for (city in citys)
            cityNames.add(city.name)
    return cityNames
    }

    fun getCityType(city : Int) : Int {
        this.city = city
        return citys[city].type
    }

    fun getTemper(strategy : Int) : Float {
        return interactor.getTemper(city, seson, strategy)
    }
}