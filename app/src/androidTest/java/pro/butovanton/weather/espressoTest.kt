package pro.butovanton.weather

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers
import org.hamcrest.Matchers.hasEntry
import org.hamcrest.core.AllOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pro.butovanton.weather.Activitys.MainActivity
import java.security.AccessController.getContext
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FileListActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun storageActivityEspressoTest() {
       onView(withText("Города")).perform(click())
       onView(withText("Добавить город")).perform(click())
       onView(withText("Введите имя")).perform(typeText(""))
    }
}