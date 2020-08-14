package pro.butovanton.weather.Activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.CitysViewModel
import pro.butovanton.weather.ViewModels.TemperViewModel

class ActivityCitys : AppCompatActivity(),
    notifyCitys {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapterCitys
    lateinit var lm : LinearLayoutManager
    lateinit var buttonAdd: Button
    lateinit var citys : MutableList<CityModel>
    val factory = Factory()
    lateinit var model : CitysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sytes)

        recyclerView = findViewById(R.id.recicler)
        adapter = RecyclerAdapterCitys(this)
        lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.adapter = adapter

        model = ViewModelProvider(this).get(CitysViewModel::class.java)
        reload()

        buttonAdd = findViewById(R.id.buttonAddCity)
        buttonAdd.setOnClickListener() {
            model.addNew("Введите имя", 0)
            reload()
        }
    }

    fun reload() {
        citys = model.getAll()
        adapter.adnotify(citys)
    }

    fun save() {
        model.setAll(citys)
    }

    override fun citys(citys: MutableList<CityModel>) {
        this.citys = citys
        save()
    }


    override fun temper(city: Int) {
        intent = Intent(this, ActivityTemper::class.java)
        intent.putExtra("city", city)
        startActivity(intent)
    }

}


