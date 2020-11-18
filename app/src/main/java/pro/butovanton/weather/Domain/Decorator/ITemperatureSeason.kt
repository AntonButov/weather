package pro.butovanton.weather.Domain.Decorator

import pro.butovanton.weather.Factory.City

interface ITemperatureSeason {
    fun getTemperatureForSeson(city: City, seson: Int): Float?
}