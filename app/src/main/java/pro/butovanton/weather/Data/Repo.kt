package pro.butovanton.weather.Data

import android.content.Context
import io.reactivex.Single
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Observer.Observable
import pro.butovanton.weather.Observer.Observer

open class Repo(val daoC: daoCity) : DataWayCitys, DataWayTemper, DataWayMain, Observable {

    companion object {
        private var instance: Repo? = null

        fun getInstance(daoC: daoCity): Repo? {
                if (instance == null) instance = Repo(daoC)
                return instance!!
            }
    }

    var observer: Observer? = null

    override fun getAll(): Single<MutableList<City>> {
        return daoC.getCitys()
    }

    override fun getCityByName(name: String): Single<City> {
        return daoC.getCityByName(name)
    }

    override fun getTemperByName(name: String): Single<MutableList<Int?>> {
            return getCityByName(name).map { city -> city.temperature }
    }

    override fun saveAll(citys: List<City>) {
        daoC.deleteAll()
        for (city: City in citys)
            daoC.insertCity(city)
    }

    override fun saveTemperByName(name: String, temper: List<Int?>) {
        daoC.getCityByName(name)
            .subscribe{ city ->
                val cityNew = city
                cityNew.temperature = temper as MutableList<Int?>
                daoC.update(cityNew)
            }
        notifyObserver()
    }

    override fun insert(city: City) {
        daoC.insertCity(city)
    }

    override fun update(city: City) {
        daoC.update(city)
    }

    override fun delete(name: String) {
        daoC.delete(name)
    }

    override fun addObserver(observer: Observer) {
        this.observer = observer
    }

    override fun removeObserver() {
        this.observer = null
    }

    override fun notifyObserver() {
        observer?.notifyObserver()
    }

}