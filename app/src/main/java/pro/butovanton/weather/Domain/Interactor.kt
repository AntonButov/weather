package pro.butovanton.weather.Domain

import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.TemperatureSeson

class Interactor(private val boundares: Boundares) : Cases {
    override fun addNew() {
        TODO("Not yet implemented")
    }

   override fun getAll(): MutableList<City> {
       return boundares.getAll()
    }

    override fun saveAll(citys: List<City>) {
        boundares.saveAll(citys)
    }

    override fun getTemper(city: Int, seson: Int): Float {
    var citys = getAll()
        if (citys.size > 0 )
        return TemperatureSeson.getTemperatureForSeson(
            getAll()[city],
            seson = seson
        )
        else return -255.toFloat();
    }

    companion object {
        private var INSTANCE: Interactor? = null

        fun get(boundares: Boundares ) : Interactor {
            if (INSTANCE == null)
                INSTANCE = Interactor(boundares)

            return INSTANCE!!
        }
    }
}