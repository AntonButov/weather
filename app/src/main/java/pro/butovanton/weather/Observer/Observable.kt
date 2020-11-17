package pro.butovanton.weather.Observer

interface Observable {
    fun addObserver(observer: Observer)
    fun removeObserver()
    fun notifyObserver()
}