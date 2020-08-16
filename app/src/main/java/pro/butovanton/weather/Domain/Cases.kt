package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City

interface Cases {
    fun addNew(name : String, type : Int)
    fun getAll() : Flowable<MutableList<City>>
    fun saveAll(citys : List<City>)
    fun update(city : City)
}