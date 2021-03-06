package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Single
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.InjectorUtils

class CitysViewModel(application: Application) : AndroidViewModel(application) {

   private val interactor =  InjectorUtils.provideInteractorCitys(application)

   fun getAll() = LiveDataReactiveStreams
      .fromPublisher(interactor.getCitys().toFlowable())

   fun update(city: CityModel, position: Int){
      interactor.update(city, position)
   }
   fun update(city: CityModel){
      interactor.update(city)
   }

   fun add(city: CityModel) {
      interactor.add(city)
   }

   fun delete(city : String) {
      interactor.delete(city)
   }
}
