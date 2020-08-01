package pro.butovanton.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface dao {

    @Query("SELECT * FROM citys")
    fun getSitys(): List<Sity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSity(sity: Sity)

    @Query("DELETE FROM citys")
    fun deleteAll()
}