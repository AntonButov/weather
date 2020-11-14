package pro.butovanton.weather.Data

import io.reactivex.Single
import pro.butovanton.weather.Factory.City

interface DataWayTemper {
    fun getAll() : Single<MutableList<City>>
    fun getCityByName(name: String): Single<City>
    fun getTemperByName(name: String): Single<MutableList<Int?>>
    fun saveTemperByName(name: String, temper: List<Int?>)
}