package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Factory.City

interface DataWayMain {
    fun getAll() : Single<MutableList<City>>
}