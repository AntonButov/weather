package pro.butovanton.weather.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.TemperViewModel

class ActivityTemper : AppCompatActivity(),
    saveTemperature {

    lateinit var recyclerViewTemper: RecyclerView
    lateinit var adapterTemper: RecyclerAdapterTemper
    lateinit var lm : LinearLayoutManager
    lateinit var temperatures : MutableList<Int?>
    lateinit var model : TemperViewModel
    var city : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temper)

        city = intent.getIntExtra("city", 0)

        model = ViewModelProvider(this).get(TemperViewModel::class.java)
        temperatures = model.getCityTemperutures(city)

        recyclerViewTemper = findViewById(R.id.reciclerTemper)
        adapterTemper =
            RecyclerAdapterTemper(
                this,
                city,
                temperatures
            )
        lm = LinearLayoutManager(this)
        recyclerViewTemper.layoutManager = lm
        recyclerViewTemper.adapter = adapterTemper
    }

    override fun temperatureSave(temperatures: MutableList<Int?>) {
        model.setCityTemperatures(city, temperatures = temperatures)
    }
}