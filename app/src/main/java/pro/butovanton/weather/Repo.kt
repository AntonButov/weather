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
    var winter = listOf(11, 0, 1)
    var spring = listOf(2, 3, 4)
    var summer = listOf(5, 6, 7)
    var autumn = listOf(8, 9, 10)
    var sesons = listOf(winter, spring, summer, autumn)

    fun getTemperatureForSeson(city : City, seson : Int): Float {
        var s = 0
        var n = 0
        for (i in 0..2) {
            var sesonList = sesons[seson]
            if (city.temperature[sesonList[i]] != null) {
                s += city.temperature[sesonList[i]]!!
                n++
            }
        }
        if (n > 0) return (s / n).toFloat()
        else return (- 255).toFloat()
    }
    }