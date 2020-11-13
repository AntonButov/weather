package pro.butovanton.weather.Presentantion

import pro.butovanton.weather.Factory.CityModel

interface notifyCitys {
    fun update(city: CityModel)
    fun update(city: CityModel, position: Int)
    fun callTemperActivity(city : String)
    fun delete(city : String)
}