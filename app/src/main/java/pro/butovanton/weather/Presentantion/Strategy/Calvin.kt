package pro.butovanton.weather.Presentantion.Strategy

class Calvin : temperatureCalculation {
    override fun calculateTemper(tC: Float): Float {
        return tC.toFloat() + 273.15.toFloat()
    }

}