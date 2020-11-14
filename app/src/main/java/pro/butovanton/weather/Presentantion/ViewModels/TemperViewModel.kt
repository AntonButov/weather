package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import pro.butovanton.weather.Domain.interactor.InteractorTemper
import pro.butovanton.weather.InjectorUtils

class TemperViewModel(app: Application, val interactor: InteractorTemper) : AndroidViewModel(app) {

   fun getCityTemperutures(city : String) =
       LiveDataReactiveStreams
           .fromPublisher(interactor.getTemper(city).toFlowable())

   fun setCityTemperatures(city : String, temperatures : MutableList<Int?>) {
      interactor.setTemper(city, temperatures)
   }

}
