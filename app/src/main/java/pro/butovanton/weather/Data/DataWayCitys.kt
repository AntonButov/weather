package pro.butovanton.weather.Data

import io.reactivex.Single
import pro.butovanton.weather.Factory.City

interface DataWayCitys {
    fun getAll() : Single<MutableList<City>>
    fun getCityByName(name: String): Single<City>
    fun saveAll(citys : List<City>)
    fun insert(city : City)
    fun update(city :City)
    fun delete(city: String)
}