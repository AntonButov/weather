package pro.butovanton.weather.Domain.interactor

import io.reactivex.Single
import pro.butovanton.weather.Data.DataWayMain
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Observer.Observable
import pro.butovanton.weather.Observer.Observer

class InteractorMain(private val dataWay: DataWayMain, private val observable: Observable) : CasesMain {

    override fun getAll(): Single<MutableList<City>> = dataWay.getAll()

    fun registerObserver(observer: Observer) = observable.addObserver(observer)

    fun unRegisterObserver() = observable.removeObserver()
    }

