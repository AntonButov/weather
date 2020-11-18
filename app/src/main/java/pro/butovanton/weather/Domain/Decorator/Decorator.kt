package pro.butovanton.weather.Domain.Decorator

import android.util.Log
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Presentantion.Strategy.temperatureCalculation

class Decorator(val temperatureSeson: ITemperatureSeason) : ITemperatureSeason {

    override fun getTemperatureForSeson(city: City, seson: Int): Float? {
       Log.d("DEBUG", "Определяем температуру за сезон.")
       return temperatureSeson.getTemperatureForSeson(city, seson)
    }

}