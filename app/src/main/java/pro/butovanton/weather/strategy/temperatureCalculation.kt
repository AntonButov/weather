package pro.butovanton.weather.strategy

interface temperatureCalculation {
    fun calculateTemper(tC: Float) : Float
}