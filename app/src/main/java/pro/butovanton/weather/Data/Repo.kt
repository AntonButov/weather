package pro.butovanton.weather.Data

import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Domain.Boundares
import pro.butovanton.weather.Factory.City

class Repo(val daoC: daoCity) : Boundares {

    override fun getAll(): Single<MutableList<City>> {
        return daoC.getCitys()
    }

    override fun saveAll(citys: List<City>) {
        daoC.deleteAll()
        for (city: City in citys)
            daoC.insertCity(city)
    }

    override fun insert(city: City) {
        daoC.insertCity(city)
    }

    override fun update(city: City) {
        daoC.update(city)
    }
}