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

    companion object {
        private var INSTANCE: Interactor? = null

        fun get(boundares: Boundares ) : Interactor {
            if (INSTANCE == null)
                INSTANCE = Interactor(boundares)

            return INSTANCE!!
        }
    }
}

