package pro.butovanton.weather.Domain

import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.Factory

class Interactor(private val boundares: Boundares) : Cases {

    override fun addNew(name: String, type: Int) {
       boundares.insert(Factory().Creat(type, name))
    }

    override fun getAll(): MutableList<City> {
       return boundares.getAll()
    }

    override fun saveAll(citys: List<City>) {
        boundares.saveAll(citys)
    }

    override fun update(city: City) {
        boundares.update(city)
    }

    override fun getTemper(city: Int, seson: Int, strategy : Int): Float {
    var citys = getAll()
        if (citys.size > 0 )
            return Strategy.calculate(strategy,
        TemperatureSeson
            .getTemperatureForSeson(
                getAll()[city],
                seson = seson
            ))
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