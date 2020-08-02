package pro.butovanton.weather

import androidx.lifecycle.ViewModel

class CitysViewModel : ViewModel() {

   val repo = Repo.instance

   fun getCitys(): MutableList<City> {
   return repo.getCitys()
   }

   fun setCitys(citys: List<City>) {
      repo.saveCitys(citys)
   }
}
