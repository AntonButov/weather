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
    @Expose
    var name: String
    val type: Int

    @SerializedName("temperature")
    @TypeConverters(Converter::class)
    @Expose
    var temperature = mutableListOf<Int>(-55, -55)
/*
    @TypeConverters(MounthConverter::class)
    val mounfs : List<Mounth> = listOf(Mounth("January"), Mounth("February"), Mounth("March"),
        Mounth("April"), Mounth("May"), Mounth("June"),
        Mounth("July"), Mounth("August"), Mounth("September"),
        Mounth("October"), Mounth("November"), Mounth("December"))

    val winter : List<Mounth> = listOf(mounfs.get(11), mounfs.get(0), mounfs.get(1))
    val spring : List<Mounth> = listOf(mounfs.get(2), mounfs.get(3), mounfs.get(4))
    val summer : List<Mounth> = listOf(mounfs.get(5), mounfs.get(6), mounfs.get(7))
    val autumn : List<Mounth> = listOf(mounfs.get(8), mounfs.get(9), mounfs.get(10))
    val sesons : List<List<Mounth>> = listOf(winter, spring, summer, autumn )
*/

    init {
        this.name = name
        this.type = type
    }
}






