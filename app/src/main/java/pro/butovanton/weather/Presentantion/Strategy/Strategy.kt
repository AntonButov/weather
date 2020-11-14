package pro.butovanton.weather.Presentantion.Strategy

class Strategy {

    companion object {
        private val calvin = Calvin()
        private val farengate = Farengate()
        fun calculate(strategy : Int, t : Float?) : Float? {
             return  when (strategy) {
                null -> null
                0 -> t
                1 -> farengate.calculateTemper(t!!)
                2 -> calvin.calculateTemper(t!!)
                 else -> throw Exception()
             }
        }
    }
}