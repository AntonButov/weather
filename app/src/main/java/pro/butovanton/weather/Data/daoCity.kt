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

    fun getTemperByName(name: String) : Single<MutableList<Int?>> {
        return getCityByName(name).map { city -> city.temperature }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: City)

    @Update
    fun update(city :City)

    @Query("DELETE FROM citys")
    fun deleteAll()
}