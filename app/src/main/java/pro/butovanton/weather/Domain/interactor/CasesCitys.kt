package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel

interface CasesCitys {
    fun add(city: CityModel)
    fun update(city: CityModel, position: Int)
    fun update(city: CityModel)
    fun delete(city : String)
    fun getCitys() : Single<MutableList<CityModel>>
}