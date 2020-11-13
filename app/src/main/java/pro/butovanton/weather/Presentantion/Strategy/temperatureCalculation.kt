package pro.butovanton.weather.Presentantion.Strategy

interface temperatureCalculation {
    fun calculateTemper(tC: Float) : Float
}