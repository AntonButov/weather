package pro.butovanton.weather.Domain

import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Activitys.Observer.ObserverTemperature
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory

class Interactor(private val boundares: Boundares) : Cases {

    private lateinit var cityCash : List<City>
    private var observer : ObserverTemperature? = null

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
        notifyObserver()
    }

    fun mapTemper(citys : MutableList<City>, city : Int) : MutableList<Int?> {
        cityCash = citys
        return citys[city].temperature
    }

    override fun getCitys(): Single<MutableList<CityModel>> {
            return getAll()
                .map { citys -> mapModels(citys) }
        }


    fun mapModels(citys: MutableList<City>) : MutableList<CityModel> {
        cityCash = citys
        var cityModels = mutableListOf<CityModel>()
        for (city : City  in citys)
            cityModels.add(mapModel(city))
        return cityModels
    }

    fun mapModel(city : City) : CityModel {
        return CityModel(city.name, city.type)
    }

    fun setCitys(cityModels: List<CityModel>) {
        for (i in 0 .. cityCash.size -1) {
            cityCash[i].name = cityModels[i].name
            cityCash[i].type = cityModels[i].type
        }
        saveAll(cityCash)
       }

    override fun update(city: City) {
        boundares.update(city)
    }

    fun registerObserver( observer : ObserverTemperature) {
        this.observer = observer
    }

    fun unregisterObserver() {
        this.observer = null
    }

    fun notifyObserver() {
        if (observer != null)
          observer?.observerNotify(" Температура изменилась. ")
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

