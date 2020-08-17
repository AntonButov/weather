package pro.butovanton.weather.Data

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Factory.City

@Dao
interface daoCity {

    @Query("SELECT * FROM citys")
    fun getSitys(): Single<MutableList<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSity(city: City)

    @Update
    fun update(city :City)

    @Query("DELETE FROM citys")
    fun deleteAll()
}