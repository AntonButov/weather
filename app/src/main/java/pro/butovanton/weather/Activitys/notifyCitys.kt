package pro.butovanton.weather.Activitys

import pro.butovanton.weather.Factory.City

interface notifyCitys {
    fun citys(cities : List<City>)
    fun temper(city : Int)
}