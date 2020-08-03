package pro.butovanton.weather

class Repo {

    private lateinit var daoC : daoCity
    fun setDao(dao: daoCity) {
    this.daoC = dao

    }

    fun getCitys() : MutableList<City> {
        return daoC.getSitys()
    }

    fun saveCitys(citys : List<City>) {
        daoC.deleteAll()
        for (city : City in citys)
            daoC.insertSity(city)
    }

    companion object {
        private var INSTANCE: Repo? = null

        val instance: Repo
            get() {
                if (INSTANCE == null) {
                    INSTANCE = Repo()
                }

                return INSTANCE!!
            }

    }

    }