package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.InjectorUtils

class TemperViewModel(app: Application) : AndroidViewModel(app) {

   val interactor =  InjectorUtils.provideInteractor(app)
   val temperatures = MutableLiveData<MutableList<Int?>>()

   private fun getCityTemperutures(city : String) {
      interactor.getTemper(city)
         .subscribe {   temper ->
      temperatures.postValue(temper)}
   }

   fun registerTemperatureObserver(city : String) : MutableLiveData<MutableList<Int?>> {
      getCityTemperutures(city)
      return temperatures
   }

   fun setCityTemperatures(city : String, temperatures : MutableList<Int?>) {
     // interactor.setTemper(city, temperatures)
   }
}
