package pro.butovanton.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pro.butovanton.weather.strategy.Calvin
import pro.butovanton.weather.strategy.Farengate

class MainViewModel : ViewModel() {

    lateinit var citys : List<City>
    var city : Int = 0
    var seson : Int = 0
    var strategy : Int = 0

    val repo = Repo.instance
    val cityNames: MutableList<String> = mutableListOf()
    val uiInfoMut = MutableLiveData<UiInfo>()
    val calvin = Calvin()
    val farengate = Farengate()

    var t = 0


    fun getCitysNames() : List<String> {
        citys = repo.getCitys()
        cityNames.clear()
        for (city in citys)
            cityNames.add(city.name)
    return cityNames
    }

    fun notifyUI() {
        if (citys.size > 0 ) {
            var tN = 0
            t = TemperatureSeson.getTemperatureForSeson(citys[city], seson).toInt()
            when (strategy) {
                0 -> tN = t
                1 -> tN = farengate.calculateTemper(t)
                2 -> tN = calvin.calculateTemper(t)
            }
            uiInfoMut.postValue(UiInfo(citys[city].type, tN))
        }
    }

    fun getInfo(): LiveData<UiInfo> {
        return uiInfoMut
    }
}