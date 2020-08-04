package pro.butovanton.weather.strategy

interface temperatureCalculation {
    fun calculateTemper(tC: Int) : Int
}