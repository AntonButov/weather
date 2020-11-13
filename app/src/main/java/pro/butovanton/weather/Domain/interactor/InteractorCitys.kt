package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory

class InteractorCitys(private val boundares: Boundares) : CasesCitys {

    override fun add(city: CityModel) {
        boundares.insert(Factory().Creat(city.type, city.name))
    }

    override fun update(city: CityModel, position: Int) {
        boundares.getAll()
            .subscribe { citys ->
                val aldCity = citys[position]
                val newCity = Factory().Creat(city.type, city.name)
                newCity.temperature = aldCity.temperature
                boundares.delete(aldCity.name)
                boundares.insert(newCity)
            }
    }

    override fun update(city: CityModel) {
        boundares.getCityByName(name = city.name)
            .subscribe { cityAld ->
                cityAld.type = city.type
                boundares.update(cityAld)
            }
    }

    override fun getCitys(): Single<MutableList<CityModel>> {
            return boundares.getAll()
                .map { citys -> mapModels(citys) }
        }
    private fun mapModels(citys: MutableList<City>) : MutableList<CityModel> {
        var cityModels = mutableListOf<CityModel>()
        citys.forEach {
            city -> cityModels.add(transformToCityModel(city))
        }
        return cityModels
    }

    private fun transformToCityModel(city : City) : CityModel {
        return CityModel(city.name, city.type)
    }

    override fun delete(city: String) {
        boundares.delete(city)
    }

}

