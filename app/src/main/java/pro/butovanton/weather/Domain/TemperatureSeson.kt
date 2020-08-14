package pro.butovanton.weather.Domain

import pro.butovanton.weather.Factory.City

class TemperatureSeson {
    companion object {
   var winter = listOf(11, 0, 1)
   var spring = listOf(2, 3, 4)
   var summer = listOf(5, 6, 7)
   var autumn = listOf(8, 9, 10)
   var sesons = listOf(
       winter,
       spring,
       summer,
       autumn
   )

        fun getTemperatureForSeson(city: City, seson: Int): Float {
            var s = 0
            var n = 0
            for (month in 0..2) {
                var sesonL = sesons[seson]
                if (city.temperature[sesonL[month]] != null) {
                    s += city.temperature[sesonL[month]]!!
                    n++
                }
            }
            if (n > 0) return (s / n).toFloat()
            else return (-255).toFloat()
        }
    }
}