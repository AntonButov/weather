package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.InjectorUtils

class TemperViewModel(application: Application) : AndroidViewModel(application) {

   val interactor =  InjectorUtils.provideInteractor(application)

   fun getCityTemperutures(city : Int): Flowable<MutableList<Int?>> {
   return interactor.getAll()
      .map { citys -> citys[city].temperature}
   }

   fun setCityTemperatures(city : Int, temperatures : MutableList<Int?>) {
      /*
      var cityM = interactor.getAll().get(city)
         cityM.temperature = temperatures
      interactor.update(cityM)

       */
   }
}
