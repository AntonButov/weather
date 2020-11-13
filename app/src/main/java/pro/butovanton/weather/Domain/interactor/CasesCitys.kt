package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel

interface CasesCitys {
    fun add(city: CityModel)
    fun getAll() : Single<MutableList<City>>
    fun saveAll(citys : List<City>)
    fun update(city: CityModel, position: Int)
    fun delete(city : String)
    fun getCitys() : Single<MutableList<CityModel>>
}