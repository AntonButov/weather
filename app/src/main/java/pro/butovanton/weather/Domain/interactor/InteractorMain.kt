package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Data.DataWayMain
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel

class InteractorMain(private val dataWay: DataWayMain) : CasesMain {

    override fun getAll(): Single<MutableList<City>> {
       return dataWay.getAll()
    }

    private fun transformToCityModel(city : City) : CityModel {
        return CityModel(city.name, city.type)
    }

}

