package pro.butovanton.weather

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citys")
open class Sity(name : String) {

    @PrimaryKey
    @NonNull
    val name : String

    init {
        this.name = name
    }
}