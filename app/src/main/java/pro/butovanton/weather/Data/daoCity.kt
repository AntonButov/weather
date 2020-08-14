package pro.butovanton.weather.Data

import androidx.room.*
import pro.butovanton.weather.Factory.City

@Dao
interface daoCity {

    @Query("SELECT * FROM citys")
    fun getSitys(): MutableList<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSity(city: City)

    @Update
    fun update(city :City)

    @Query("DELETE FROM citys")
    fun deleteAll()
}