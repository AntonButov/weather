package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel

interface Cases {
    fun addNew(name : String, type : Int)
    fun getAll() : Single<MutableList<City>>
    fun saveAll(citys : List<City>)
    fun update(city : City)
    fun delete(city : Int)
    fun getTemper( city : String): Single<MutableList<Int?>>
    fun setTemper(city : Int, temper : MutableList<Int?>)
    fun getCitys() : Single<MutableList<CityModel>>
}