package pro.butovanton.weather.Observer

interface Observer {
    fun registerObserver(observer: Observer)
    fun unRegisterObserver()
    fun notifyObserver()
}