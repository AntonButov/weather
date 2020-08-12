package pro.butovanton.weather

class TemperatureSeson {
    companion object {
   private var winter = listOf(11, 0, 1)
   private var spring = listOf(2, 3, 4)
   private var summer = listOf(5, 6, 7)
   private var autumn = listOf(8, 9, 10)
   private var sesons = listOf(winter, spring, summer, autumn)

        fun getTemperatureForSeson(city: City, seson: Int): Float {
            var s = 0
            var n = 0
            for (i in 0..2) {
                var sesonList = sesons[seson]
                if (city.temperature[sesonList[i]] != null) {
                    s += city.temperature[sesonList[i]]!!
                    n++
                }
            }
            if (n > 0) return (s / n).toFloat()
            else return (-255).toFloat()
        }
    }
}