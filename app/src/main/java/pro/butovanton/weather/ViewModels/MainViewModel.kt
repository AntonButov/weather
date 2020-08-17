package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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

    fun getDataFromBD() {
        subscription.add(interactor.getAll()
            .map {citys ->
                mapNames(citys)
            }
            .subscribe { cityNames ->
                citysNamesM.postValue(cityNames)
            notifyType()
            notifyTemperature()})
    }

    fun mapNames(citys : List<City>) : List<String> {
        citysCash = citys
        cityNames.clear()
        for (city in citys)
            cityNames.add(city.name)
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

    override fun onCleared() {
        super.onCleared()
        interactor.getAll()
            .subscribe()
            .dispose()
        if (!subscription.isDisposed)
            subscription.dispose()
    }
}