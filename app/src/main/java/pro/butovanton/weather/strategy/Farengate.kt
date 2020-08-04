package pro.butovanton.weather.strategy

class Farengate : temperatureCalculation {
    override fun calculateTemper(tC: Int): Int {
        return tC + 32
    }
}