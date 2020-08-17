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

class TemperViewModel(application: Application) : AndroidViewModel(application) {

   val interactor =  InjectorUtils.provideInteractor(application)
   val temperatures = MutableLiveData<MutableList<Int?>>()

   fun getCityTemperutures(city : Int) {
      interactor.getTemper(city)
         .subscribe {   temper ->
      temperatures.postValue(temper)}
   }

   fun registerTemperatureObserver(city : Int) : MutableLiveData<MutableList<Int?>> {
      getCityTemperutures(city)
      return temperatures
   }

   fun setCityTemperatures(city : Int, temperatures : MutableList<Int?>) {
      interactor.setTemper(city, temperatures)
   }
}
