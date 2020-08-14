package pro.butovanton.weather.Activitys.Strategy

class Farengate : temperatureCalculation {
    override fun calculateTemper(tC: Float): Float {
        return (tC * 9 /5  + 32).toFloat()
    }
}