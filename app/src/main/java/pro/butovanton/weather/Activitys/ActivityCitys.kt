package pro.butovanton.weather.Activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_sytes.*
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.CitysViewModel


class ActivityCitys : AppCompatActivity(),
    notifyCitys {

    lateinit var adapter: RecyclerAdapterCitys
    lateinit var lm: LinearLayoutManager
    var citys = mutableListOf<CityModel>()
    val factory = Factory()
    lateinit var model: CitysViewModel
    var d = CompositeDisposable()

    val ACTIVITY_TEMPERATURE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sytes)

        adapter = RecyclerAdapterCitys(this)
        lm = LinearLayoutManager(this)
        reciclerCitys.layoutManager = lm
        reciclerCitys.adapter = adapter

        model = ViewModelProvider(this).get(CitysViewModel::class.java)

        buttonAddCity.setOnClickListener() {
            model.addNew("Введите имя", 0)
            refreshDB()
        }
    }

    override fun citys(citys: MutableList<CityModel>) {
        this.citys = citys
    }

    override fun onResume() {
        super.onResume()
        refreshDB()
    }

    fun refreshDB() {
        model.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { citys ->
                this.citys = citys
                adapter.adnotify(citys)
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        model.setAll(citys)
    }

    override fun temper(city: Int) {
        intent = Intent(this, ActivityTemper::class.java)
        intent.putExtra("city", city)
        startActivityForResult(intent, ACTIVITY_TEMPERATURE)
    }

    override fun delete(city: Int) {
        model.delete(city)
        d.clear()
    }
}

