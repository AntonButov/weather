package pro.butovanton.weather.Data

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import pro.butovanton.weather.Factory.City

@Dao
interface daoCity {

    @Query("SELECT * FROM citys")
    fun getCitys(): Single<MutableList<City>>

    @Query("SELECT * FROM citys where name = :name LIMIT 1")
    fun getCityByName(name: String): Single<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: City)

    @Update
    fun update(city :City)

    @Query("DELETE FROM citys where name = :name")
    fun delete(name: String)

    @Query("DELETE FROM citys")
    fun deleteAll()
}