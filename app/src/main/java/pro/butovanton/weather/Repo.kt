package pro.butovanton.weather

class Repo {
    val mounfs : List<Mounth> = listOf(Mounth("January"), Mounth("February"), Mounth("March"),
                                       Mounth("April"), Mounth("May"), Mounth("June"),
                                       Mounth("July"), Mounth("August"), Mounth("September"),
                                       Mounth("October"), Mounth("November"), Mounth("December"))
    val winter : List<Mounth> = listOf(mounfs.get(11), mounfs.get(0), mounfs.get(1))
    val spring : List<Mounth> = listOf(mounfs.get(2), mounfs.get(3), mounfs.get(4))
    val summer : List<Mounth> = listOf(mounfs.get(5), mounfs.get(6), mounfs.get(7))
    val autumn : List<Mounth> = listOf(mounfs.get(8), mounfs.get(9), mounfs.get(10))
    val sesons : List<List<Mounth>> = listOf(winter, spring, summer, autumn )

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