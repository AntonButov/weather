package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Factory.City

interface Boundares {
    fun getAll() : Single<MutableList<City>>
    fun getCityByName(name: String): Single<City>
    fun getTemperByName(name: String): Single<MutableList<Int?>>
    fun saveAll(citys : List<City>)
    fun insert(city : City)
    fun update(city :City)
}