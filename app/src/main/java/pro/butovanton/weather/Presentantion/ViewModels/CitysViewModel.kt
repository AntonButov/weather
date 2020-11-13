package pro.butovanton.weather.Presentantion.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Single
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.InjectorUtils

class CitysViewModel(application: Application) : AndroidViewModel(application) {

   private val interactor =  InjectorUtils.provideInteractorCitys(application)

   fun getAll() : Single<MutableList<CityModel>> {
      return interactor.getCitys()
   }
   fun update(city: CityModel, position: Int){
      interactor.update(city, position)
   }

   fun add(city: CityModel) {
      interactor.add(city)
   }

   fun delete(city : String) {
      interactor.delete(city)
   }
}
