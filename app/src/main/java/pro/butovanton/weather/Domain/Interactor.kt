package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.Factory

class Interactor(private val boundares: Boundares) : Cases {

    override fun addNew(name: String, type: Int) {
       boundares.insert(Factory().Creat(type, name))
    }

    override fun getAll(): Flowable<MutableList<City>> {
       return boundares.getAll()
    }

    override fun saveAll(citys: List<City>) {
        boundares.saveAll(citys)
    }

    override fun update(city: City) {
        boundares.update(city)
    }

    override fun getTemper(city: Int, seson: Int, strategy : Int): Flowable<Float> {
    var citys = getAll()

        return citys.map { citys ->
            if (citys.size > 0 )
                 Strategy.calculate(strategy,
                    TemperatureSeson
                        .getTemperatureForSeson(
                            citys[city],
                            seson = seson
                        ))
            else -255.toFloat();
             }
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

private fun <T> Flowable<T>.filter(citys: Flowable<T>) {

}
