package pro.butovanton.weather.strategy

class Calvin : temperatureCalculation {
    override fun calculateTemper(tC: Int): Int {
        return tC - 273
    }

}