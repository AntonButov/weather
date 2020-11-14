package pro.butovanton.weather.Domain.Factory

import pro.butovanton.weather.Factory.City

class TestCity : City("test", 0) {

    init {
        temperature = mutableListOf<Int?>(10, 10, 20, 20, 20, 30, 30, 30, 25, 25, 25, 10)
    }
}