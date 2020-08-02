package pro.butovanton.weather

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citys")
open class City(name : String, type : Int) {

    @PrimaryKey
    @NonNull
    var name : String
    val type : Int

    init {
        this.name = name
        this.type = type
    }

}