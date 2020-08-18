package pro.butovanton.weather.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.InjectorUtils

class CitysViewModel(application: Application) : AndroidViewModel(application) {

   val interactor =  InjectorUtils.provideInteractor(application)

   fun addNew(name : String, type : Int) {
      interactor.addNew(name, type)
   }

   fun getAll() : Single<MutableList<CityModel>> {
      return interactor.getCitys()
   }

   fun setAll(cityModels: List<CityModel>) {
      interactor.setCitys(cityModels)
   }

   fun delete(city : Int) {

      interactor.delete(city)
   }
}
