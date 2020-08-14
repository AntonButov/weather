package pro.butovanton.weather.Domain

import pro.butovanton.weather.Factory.City

interface Cases {
    fun addNew()
    fun getAll() : List<City>
    fun saveAll(citys : List<City>)
    fun getTemper(city : Int, seson : Int) : Float
}