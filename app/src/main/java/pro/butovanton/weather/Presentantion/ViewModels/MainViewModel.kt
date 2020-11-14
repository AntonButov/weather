package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Domain.TemperatureSeson
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.InjectorUtils

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val interactor = InjectorUtils.provideInteractor(application)

  fun getCitys() = LiveDataReactiveStreams
      .fromPublisher(interactor.getAll().toFlowable())

 }