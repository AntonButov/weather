package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City

interface Boundares {
    fun getAll() : Flowable<MutableList<City>>
    fun saveAll(citys : List<City>)
    fun insert(city : City)
    fun update(city :City)
}