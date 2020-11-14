package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import pro.butovanton.weather.InjectorUtils

class TemperViewModel(app: Application) : AndroidViewModel(app) {

  private val interactor =  InjectorUtils.provideInteractorTemper(app)
  val temperatures = MutableLiveData<MutableList<Int?>>()

   private fun getCityTemperutures(city : String) =
       LiveDataReactiveStreams
           .fromPublisher(interactor.getTemper(city)
               .toFlowable());


   fun setCityTemperatures(city : String, temperatures : MutableList<Int?>) {
      interactor.setTemper(city, temperatures)
   }

   fun registerTemperatureObserver(city : String) : MutableLiveData<MutableList<Int?>> {
      getCityTemperutures(city)
      return temperatures
   }
}
