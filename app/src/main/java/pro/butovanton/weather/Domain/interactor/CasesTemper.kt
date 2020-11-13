package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel

interface CasesTemper {
    fun getTemper( city : String): Single<MutableList<Int?>>
    fun setTemper(city : String, temper : MutableList<Int?>)
}