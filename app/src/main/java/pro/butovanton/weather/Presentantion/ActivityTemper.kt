package pro.butovanton.weather.Presentantion

import android.R.id
import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_temper.*
import pro.butovanton.weather.InjectorUtils
import pro.butovanton.weather.Presentantion.ViewModels.TemperViewModel
import pro.butovanton.weather.R


class ActivityTemper : AppCompatActivity(),
    saveTemperature {

    val model : TemperViewModel by viewModels { InjectorUtils.TemperModelFactory(application) }
    lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temper)
        city =  intent.getStringExtra("city")
            model.getCityTemperutures(city).observe(this, Observer { temperatures ->
                val adapterTemper = AdapterTemper(this, temperatures)
                reciclerTemper.layoutManager = LinearLayoutManager(this)
                reciclerTemper.adapter = adapterTemper
            })
    }

    override fun temperatureSave(temperatures: MutableList<Int?>) {
        model.setCityTemperatures(city, temperatures = temperatures)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}

