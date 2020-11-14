package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Data.DataWayTemper

open class InteractorTemper(private val dataWay: DataWayTemper) : CasesTemper {

    override fun getTemper(name: String): Single<MutableList<Int?>> {
        return dataWay.getTemperByName(name)
    }
    override fun setTemper(name: String, temper: MutableList<Int?>) {
        dataWay.saveTemperByName(name, temper)
            //notifyObserver()
    }

}

