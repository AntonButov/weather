package pro.butovanton.weather.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pro.butovanton.weather.Factory.City

@Dao
interface daoCity {

    @Query("SELECT * FROM citys")
    fun getSitys(): MutableList<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSity(city: City)

    @Query("DELETE FROM citys")
    fun deleteAll()
}