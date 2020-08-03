package pro.butovanton.weather

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

        @TypeConverter
        fun fromInt(temperatures: MutableList<Int>): String {
            return Gson().toJson(temperatures)
        }

        @TypeConverter
        fun toInt(temperat: String): MutableList<Int> {
            val type = object : TypeToken<MutableList<Int>>() {}.type
            return Gson().fromJson(temperat, type)
        }
}

