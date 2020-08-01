package pro.butovanton.weather

import androidx.room.Room
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun factoryTest() {
        var sity: Sity
        val factory = Factory()
        val name = "Test"

        sity = factory.Creat(SityType.Small, name)
        assertTrue(sity is Small )

        sity = factory.Creat(SityType.Medium, name)
        assertTrue(sity is Medium)

        sity = factory.Creat(SityType.Big, name)
        assertTrue(sity is Big)
    }

    @Test
    fun sezonsTest()  {
      val repo = Repo()
      assertTrue(repo.sesons.size == 4)
      assertTrue(repo.autumn.size == 3)
      assertTrue(repo.summer.size == 3)
      assertTrue(repo.spring.size == 3)
      assertTrue(repo.winter.size == 3)
      assertTrue(repo.mounfs.size == 12)
    }


}