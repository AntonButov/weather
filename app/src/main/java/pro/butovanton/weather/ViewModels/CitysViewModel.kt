package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.InjectorUtils

class CitysViewModel(application: Application) : AndroidViewModel(application) {

   val interactor =  InjectorUtils.provideInteractor(application)
   lateinit var cityCash: MutableList<City>

   fun addNew(name : String, type : Int) {
      interactor.addNew(name, type)
   }

   fun getAll() : Flowable<MutableList<CityModel>> {
      return interactor.getAll()
         .map { citys -> mapModels(citys) }
   }

   fun mapModels(citys: MutableList<City>) : MutableList<CityModel> {
      cityCash = citys
      var cityModels = mutableListOf<CityModel>()
      for (city : City  in citys)
         cityModels.add(mapModel(city))
      return cityModels
   }

   fun mapModel(city : City) : CityModel {
    return CityModel(city.name, city.type)
   }

   fun setAll(cityModels: List<CityModel>) {
      for (i in 0 .. cityCash.size -1) {
         cityCash[i].name = cityModels[i].name
         cityCash[i].type = cityModels[i].type
      }
      interactor.saveAll(cityCash)
   }
}
