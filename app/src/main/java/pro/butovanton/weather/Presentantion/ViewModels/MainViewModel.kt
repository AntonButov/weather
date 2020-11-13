package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Domain.TemperatureSeson
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.InjectorUtils

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val subscription = CompositeDisposable()

    var city : Int = 0
    var seson : Int = 0
    var strategy : Int = 0
    var temper = MutableLiveData<Float>()
    var type = MutableLiveData<Int>()
    var citysNamesM = MutableLiveData<List<String>>()

    val interactor = InjectorUtils.provideInteractor(application)
    val cityNames: MutableList<String> = mutableListOf()

    var citysCash = listOf<City>()

    fun getCitysNames() : LiveData<List<String>> {
        getDataFromBD()
        return citysNamesM
        }

    private fun getDataFromBD() {
        subscription.add(interactor.getAll()
            .map {citys ->
                mapNames(citys)
            }
            .subscribe { cityNames ->
                citysNamesM.postValue(cityNames)
            notifyType()
            notifyTemperature()})
    }

    private fun mapNames(citys : List<City>) : List<String> {
        citysCash = citys
        cityNames.clear()
        citys.forEach { city ->
            cityNames.add(city.name)
        }
    return cityNames
    }

    fun setCityAndNotify(city : Int) {
        this.city = city
        notifyType()
    }

    fun notifyType() {
        if (citysCash.size > 0)
          type.postValue(citysCash[city!!].type)
    }

    fun setSesonAndNotify(seson : Int) {
        this.seson = seson
        notifyTemperature()
    }

    fun setStrategyAndNotify() {
        notifyTemperature()
    }

    fun notifyTemperature() {
        if (citysCash.size > 0)
            temper.value = TemperatureSeson
                .getTemperatureForSeson(city = citysCash[city!!],seson = seson!!)
    }

    fun registerObserverCityType() : LiveData<Int> {
        return type
    }

    fun registerObserverTemperature() : LiveData<Float> {
        return temper
    }

    fun registerObserver(observer : ObserverTemperature) {
        interactor.registerObserver(observer)
    }

    fun unregisterObserver() {
        interactor.unregisterObserver()
    }

}