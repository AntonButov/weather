package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.InjectorUtils

class CitysViewModel(application: Application) : AndroidViewModel(application) {

   val interactor =  InjectorUtils.provideInteractor(application)

   fun addNew(name : String, type : Int) {
      interactor.addNew(name, type)
   }

   fun getCitys(): MutableList<City> {
   return interactor.getAll()
   }

   fun setCitys(citys: List<City>) {
      interactor.saveAll(citys)
   }
}
