package pro.butovanton.weather

enum class SityType { Small, Medium, Big }

class Factory {

    fun Creat(type: SityType, name: String): City {

     when (type) {
         SityType.Small -> return Small(name)
         SityType.Big -> return Big(name)
         SityType.Medium -> return Medium(name)
     }
    }
}

