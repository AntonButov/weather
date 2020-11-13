package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Factory.City

interface Boundares {
    fun getAll() : Single<MutableList<City>>
    fun getCityByName(name: String): Single<City>
    fun getTemperByName(name: String): Single<MutableList<Int?>>
    fun saveAll(citys : List<City>)
    fun saveTemperByName(name: String, temper: List<Int?>)
    fun insert(city : City)
    fun update(city :City)
    fun delete(city: String)
}