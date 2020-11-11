package pro.butovanton.weather.Activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_temper.*
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.TemperViewModel


class ActivityTemper : AppCompatActivity(),
    saveTemperature {

    val model : TemperViewModel by viewModels()

    lateinit var adapterTemper: RecyclerAdapterTemper
    lateinit var lm : LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temper)

        val city = intent.getStringExtra("city")
        city?.let {
            model.registerTemperatureObserver(city).observe(this, Observer { temperatures ->
                adapterTemper = RecyclerAdapterTemper(this, temperatures)

                lm = LinearLayoutManager(this)
                reciclerTemper.layoutManager = lm
                reciclerTemper.adapter = adapterTemper
            })
        }
    }

    override fun temperatureSave(temperatures: MutableList<Int?>) {
      //  model.setCityTemperatures(city, temperatures = temperatures)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}

