package pro.butovanton.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pro.butovanton.weather.strategy.Calvin
import pro.butovanton.weather.strategy.Farengate
import pro.butovanton.weather.strategy.Strategy

class MainViewModel : ViewModel() {

    lateinit var citys : List<City>
    var city : Int = 0
    var seson : Int = 0
    var strategy : Int = 0

    val repo = Repo.instance
    val cityNames: MutableList<String> = mutableListOf()
    val uiInfoMut = MutableLiveData<UiInfo>()

    fun getCitysNames() : List<String> {
        citys = repo.getCitys()
        cityNames.clear()
        for (city in citys)
            cityNames.add(city.name)
    return cityNames
    }

    fun notifyUI() {
        if (citys.size > 0 ) {
            var t : Float
            t = TemperatureSeson.getTemperatureForSeson(citys[city], seson)
            t = Strategy.calculate(strategy, t)
            uiInfoMut.postValue(UiInfo(citys[city].type, t.toInt()))
        }
    }

    fun getInfo(): LiveData<UiInfo> {
        return uiInfoMut
    }
}