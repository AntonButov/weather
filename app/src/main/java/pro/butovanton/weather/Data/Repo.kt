package pro.butovanton.weather.Data

import io.reactivex.Flowable
import pro.butovanton.weather.Domain.Boundares
import pro.butovanton.weather.Factory.City

class Repo(val daoC : daoCity) : Boundares {

    companion object {
        private var INSTANCE: Repo? = null

          fun get(daoC : daoCity) : Repo {
                if (INSTANCE == null)
                    INSTANCE = Repo(daoC)

                return INSTANCE!!
            }
    }

    override fun getAll(): Flowable<MutableList<City>> {
        return daoC.getSitys()
    }

    override fun saveAll(citys: List<City>) {
        daoC.deleteAll()
        for (city : City in citys)
            daoC.insertSity(city)
    }

    override fun insert(city: City) {
        daoC.insertSity(city)
    }

    override fun update(city: City) {
        daoC.update(city)
    }
}