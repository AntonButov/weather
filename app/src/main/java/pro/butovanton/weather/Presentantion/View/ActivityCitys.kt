package pro.butovanton.weather.Presentantion.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sytes.*
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory
import pro.butovanton.weather.Presentantion.AdapterCitys
import pro.butovanton.weather.R
import pro.butovanton.weather.Presentantion.ViewModels.CitysViewModel


class ActivityCitys : AppCompatActivity(), notifyCitys {

    val model : CitysViewModel by viewModels()

    lateinit var adapter: AdapterCitys
    val factory = Factory()

    val ACTIVITY_TEMPERATURE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sytes)

        adapter = AdapterCitys(context = this, returnData = this as notifyCitys)
        val lm = LinearLayoutManager(this)
        reciclerCitys.layoutManager = lm
        reciclerCitys.adapter = adapter

        buttonAddCity.setOnClickListener() {
            model.add(CityModel("Введите имя", 0))
            notifyAdapter()
        }

        notifyAdapter()
    }

    private fun notifyAdapter() {
        model.getAll()
            .observe(this, object : Observer<List<CityModel>> {
                override fun onChanged(citys: List<CityModel>?) {
                    adapter.setData(citys as MutableList<CityModel>)
                }
            })
    }

    override fun update(city: CityModel) {
        model.update(city)
    }

    override fun update(city: CityModel, position: Int) {
        model.update(city, position)
    }

    override fun callTemperActivity(city: String) {
        intent = Intent(this, ActivityTemper::class.java)
        intent.putExtra("city", city)
        startActivityForResult(intent, ACTIVITY_TEMPERATURE)
    }

    override fun delete(city: String) {
        model.delete(city)
    }
}

