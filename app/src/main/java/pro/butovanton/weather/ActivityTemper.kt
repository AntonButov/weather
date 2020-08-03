package pro.butovanton.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityTemper : AppCompatActivity(), saveTemperature {

    lateinit var recyclerViewTemper: RecyclerView
    lateinit var adapterTemper: RecyclerAdapterTemper
    lateinit var lm : LinearLayoutManager
    lateinit var citys : MutableList<City>
    lateinit var model : CitysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temper)

        val city = intent.getIntExtra("city", 0)

        model = ViewModelProvider(this).get(CitysViewModel::class.java)
        citys = model.getCitys()

        recyclerViewTemper = findViewById(R.id.reciclerTemper)
        adapterTemper = RecyclerAdapterTemper(this, city, citys)
        lm = LinearLayoutManager(this)
        recyclerViewTemper.layoutManager = lm
        recyclerViewTemper.adapter = adapterTemper
    }

    override fun temperatureSave(citys: List<City>) {
        model.setCitys(citys)
    }
}