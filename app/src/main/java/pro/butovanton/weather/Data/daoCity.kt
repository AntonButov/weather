package pro.butovanton.weather.Data

import androidx.room.*
import io.reactivex.Flowable
import pro.butovanton.weather.Factory.City

@Dao
interface daoCity {

    @Query("SELECT * FROM citys")
    fun getSitys(): Flowable<MutableList<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSity(city: City)

    @Update
    fun update(city :City)

    @Query("DELETE FROM citys")
    fun deleteAll()
}