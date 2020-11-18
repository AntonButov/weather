package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import pro.butovanton.weather.InjectorUtils
import pro.butovanton.weather.Observer.Observable
import pro.butovanton.weather.Observer.Observer

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val interactor = InjectorUtils.provideInteractorMain(application)
    val message = MutableLiveData<String>("")

    val observer = object : Observer {
        override fun registerObserver(observer: Observer) { }

        override fun unRegisterObserver() {
            interactor.unRegisterObserver()
        }

        override fun notifyObserver() {
            message.value = "Температура изменилась."
        }
    }

    init {
        interactor.registerObserver(observer)
    }

  fun getCitys() = LiveDataReactiveStreams
      .fromPublisher(interactor.getAll().toFlowable())

    override fun onCleared() {
        super.onCleared()
        observer.unRegisterObserver()
    }
}