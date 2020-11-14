package pro.butovanton.weather.Data

import io.reactivex.Single
import pro.butovanton.weather.Domain.interactor.DataWay
import pro.butovanton.weather.Domain.interactor.DataWayMain
import pro.butovanton.weather.Domain.interactor.DataWayTemper
import pro.butovanton.weather.Factory.City

class Repo(val daoC: daoCity) : DataWay, DataWayTemper, DataWayMain {

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
}