package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel

interface CasesMain {

    fun getAll() : Single<MutableList<City>>
    fun getCitys() : Single<MutableList<CityModel>>
}