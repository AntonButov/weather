package pro.butovanton.weather.Factory

class Factory {


    fun Creat(type: Int, name: String): City {

        return when (type) {
            0 -> Small(name, type)
            1 -> Medium(name, type)
            2 -> Big(name, type)
            else -> City(name, type)
        }
    }
}

