package pro.butovanton.weather

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type


@Entity(tableName = "citys")
open class City(name : String, type : Int) {

    @PrimaryKey
    @NonNull
    var name: String
    var type: Int

    @SerializedName("temperature")
    @TypeConverters(Converter::class)
    var temperature = mutableListOf<Int?>(null, null, null, null, null, null, null, null, null, null, null, null)

    init {
        this.name = name
        this.type = type
    }
}






