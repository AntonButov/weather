package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.Factory

class Interactor(private val boundares: Boundares) : Cases {

    private lateinit var cityCash : List<City>

    override fun addNew(name: String, type: Int) {
       boundares.insert(Factory().Creat(type, name))
    }

    override fun getAll(): Single<MutableList<City>> {
       return boundares.getAll()
    }

    override fun saveAll(citys: List<City>) {
        boundares.saveAll(citys)
    }

    override fun getTemper(city: Int) : Single<MutableList<Int?>> {
        return getAll()
            .map { citys ->
                mapTemper(citys, city)
            }
    }

    override fun setTemper(city: Int, temper: MutableList<Int?>) {
        cityCash[city].temperature = temper
        saveAll(cityCash)
    }


    fun mapTemper(citys : MutableList<City>, city : Int) : MutableList<Int?> {
        cityCash = citys
        return citys[city].temperature
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

