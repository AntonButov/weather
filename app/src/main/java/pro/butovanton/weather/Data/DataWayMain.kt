package pro.butovanton.weather.Data

import io.reactivex.Single
import pro.butovanton.weather.Factory.City

interface DataWayMain {
    fun getAll() : Single<MutableList<City>>
}