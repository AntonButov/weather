package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.InjectorUtils

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var city : Int = 0
    var seson : Int = 0
    var strategy : Int = 0

    val interactor = InjectorUtils.provideInteractor(application)
    val cityNames: MutableList<String> = mutableListOf()

    fun getCitysNames() : Flowable<List<String>> {
        return interactor.getAll()
            .map {citys ->
                mapNames(citys)
            }
    }

    fun mapNames(citys : List<City>) : List<String> {
        cityNames.clear()
        for (city in citys)
            cityNames.add(city.name)
    return cityNames
    }

    fun getCityType(city : Int) : Flowable<Int> {
        this.city = city
        return interactor.getAll()
            .filter { c -> c.size > 0 }
            .map { citys ->  citys[city].type }
    }

    fun cityType(citys : List<City>, city : Int) : Int {
        if (citys.size > 0)
            return 0
        else
            return citys[city].type
    }

    fun getTemper(strategy : Int) : Float {
        return 0.toFloat()
        interactor.getTemper(city, seson, strategy)
    }
}