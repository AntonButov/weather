package pro.butovanton.weather.strategy

class Strategy {

    companion object {
        private val calvin = Calvin()
        private val farengate = Farengate()
        fun calculate(strategy : Int, t : Float) : Float {
             return  when (strategy) {
                0 -> t
                1 -> farengate.calculateTemper(t)
                2 -> calvin.calculateTemper(t)
                 else -> t
             }
        }
    }
}