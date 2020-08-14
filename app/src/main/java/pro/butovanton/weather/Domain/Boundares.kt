package pro.butovanton.weather.Domain

import pro.butovanton.weather.Factory.City

interface Boundares {
    fun getAll() : MutableList<City>
    fun saveAll(citys : List<City>)
}