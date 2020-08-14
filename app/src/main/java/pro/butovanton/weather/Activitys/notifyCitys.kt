package pro.butovanton.weather.Activitys

import pro.butovanton.weather.Factory.CityModel

interface notifyCitys {
    fun citys(cities : MutableList<CityModel>)
    fun temper(city : Int)
}