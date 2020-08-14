package pro.butovanton.weather

import org.junit.Test

import org.junit.Assert.*
import pro.butovanton.weather.Data.Repo
import pro.butovanton.weather.Factory.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun factoryTest() {
        var city: City
        val factory = Factory()
        val name = "Test"

        city = factory.Creat(0, name)
        assertTrue(city is Small)

        city = factory.Creat(1, name)
        assertTrue(city is Medium)

        city = factory.Creat(2, name)
        assertTrue(city is Big)
    }

    @Test
    fun sezonsTest()  {
      val repo = Repo.instance
      assertTrue(repo.sesons.size == 4)
      assertTrue(repo.autumn.size == 3)
      assertTrue(repo.summer.size == 3)
      assertTrue(repo.spring.size == 3)
      assertTrue(repo.winter.size == 3)
      assertTrue(repo.mounfs.size == 12)
    }
}