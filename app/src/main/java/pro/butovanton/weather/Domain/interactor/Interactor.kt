package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory

class Interactor(private val boundares: Boundares) : Cases {

    private lateinit var cityCash : MutableList<City>
    private var observer : ObserverTemperature? = null

    override fun getAll(): Single<MutableList<City>> {
       return boundares.getAll()
    }

    override fun getCitys(): Single<MutableList<CityModel>> {
            return getAll()
                .map { citys -> mapModels(citys) }
        }
    fun mapModels(citys: MutableList<City>) : MutableList<CityModel> {
        cityCash = citys
        var cityModels = mutableListOf<CityModel>()
        citys.forEach {
            city -> cityModels.add(transformToCityModel(city))
        }
        return cityModels
    }

    private fun transformToCityModel(city : City) : CityModel {
        return CityModel(city.name, city.type)
    }

    fun registerObserver( observer : ObserverTemperature) {
        this.observer = observer
    }
    fun unregisterObserver() {
        this.observer = null
    }
    fun notifyObserver() {
        observer.let {
          it!!.observerNotify(" Температура изменилась. ")}
    }
}

