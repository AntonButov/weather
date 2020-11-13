package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory

class InteractorTemper(private val boundares: Boundares) : CasesTemper {

        private var observer : ObserverTemperature? = null

    override fun getTemper(name: String): Single<MutableList<Int?>> {
        return boundares.getTemperByName(name)
    }
    override fun setTemper(name: String, temper: MutableList<Int?>) {
        boundares.saveTemperByName(name, temper)
        notifyObserver()
    }

    fun registerObserver( observer : ObserverTemperature) {
        this.observer = observer
    }
    fun unregisterObserver() {
        this.observer = null
    }
    fun notifyObserver() {
        observer.let {
          it!!.observerNotify(" Температура изменилась. ")}
    }


}

