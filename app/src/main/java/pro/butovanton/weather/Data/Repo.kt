package pro.butovanton.weather.Data

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

    override fun getAll(): MutableList<City> {
        return daoC.getSitys()
    }

    override fun saveAll(citys: List<City>) {
        daoC.deleteAll()
        for (city : City in citys)
            daoC.insertSity(city)
    }
}