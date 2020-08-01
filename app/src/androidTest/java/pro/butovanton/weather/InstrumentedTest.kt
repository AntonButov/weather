package pro.butovanton.weather

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import pro.butovanton.weather.App.Companion.db

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun dbTest() {
        val db = Room.databaseBuilder(appContext, AppDatabase::class.java,"sitys").allowMainThreadQueries().build()
        val dao = db.getDao()
        dao.insertSity(Sity("test"))
        var sitys: List<Sity> = dao.getSitys()
        var sity = sitys.get(0)
        dao.deleteAll()
        assertTrue(sity.name.equals("test"))
    }

}