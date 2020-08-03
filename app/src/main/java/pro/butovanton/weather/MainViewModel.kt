package pro.butovanton.weather

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    lateinit var citys : List<City>
    var city : Int = 0
    val repo = Repo.instance
    val cityNames: MutableList<String> = mutableListOf()

    fun getCitysNames() : List<String> {
        citys = repo.getCitys()
        cityNames.clear()
        for (city in citys)
            cityNames.add(city.name)
    return cityNames
    }
}