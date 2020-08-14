package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.InjectorUtils

class CitysViewModel(application: Application) : AndroidViewModel(application) {

   val interactor =  InjectorUtils.provideInteractor(application)

   fun addNew(name : String, type : Int) {
      interactor.addNew(name, type)
   }

   fun getAll() : MutableList<CityModel> {
      return mapModels(interactor.getAll())
   }

   fun mapModels(citys : List<City>) : MutableList<CityModel> {
      var cityModels = mutableListOf<CityModel>()
      for (city : City  in citys)
         cityModels.add(mapModel(city))
      return cityModels
   }

   fun mapModel(city : City) : CityModel {
    return CityModel(city.name, city.type)
   }

   fun setAll(cityModels: List<CityModel>) {
      var citys = interactor.getAll()
      for (i  in 0 .. citys.size - 1 ) {
         citys[i].name = cityModels[i].name
         citys[i].type = cityModels[i].type
      }
      interactor.saveAll(citys)
   }

}
