package pro.butovanton.weather.Factory

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
import pro.butovanton.weather.Data.Converter
import java.io.Serializable
import java.lang.reflect.Type

open class CityModel(name : String, type : Int) {

    var name: String
    var type: Int

    init {
        this.name = name
        this.type = type
    }
}






