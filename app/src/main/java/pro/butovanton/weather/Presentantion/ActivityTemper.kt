package pro.butovanton.weather.Presentantion

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_temper.*
import pro.butovanton.weather.R
import pro.butovanton.weather.Presentantion.ViewModels.TemperViewModel


class ActivityTemper : AppCompatActivity(),
    saveTemperature {

    val model : TemperViewModel by viewModels()
    lateinit var city: String
    lateinit var adapterTemper: AdapterTemper
    lateinit var lm : LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temper)
        city =  intent.getStringExtra("city")
            model.registerTemperatureObserver(city).observe(this, Observer { temperatures ->
                adapterTemper = AdapterTemper(this, temperatures)

                lm = LinearLayoutManager(this)
                reciclerTemper.layoutManager = lm
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

