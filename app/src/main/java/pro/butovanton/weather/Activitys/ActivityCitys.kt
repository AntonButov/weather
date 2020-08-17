package pro.butovanton.weather.Activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.Factory.Factory
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.CitysViewModel


class ActivityCitys : AppCompatActivity(),
    notifyCitys {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapterCitys
    lateinit var lm: LinearLayoutManager
    lateinit var buttonAdd: Button
    var citys = mutableListOf<CityModel>()
    val factory = Factory()
    lateinit var model: CitysViewModel

    val ACTIVITY_TEMPERATURE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sytes)

        recyclerView = findViewById(R.id.recicler)
        adapter = RecyclerAdapterCitys(this)
        lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.adapter = adapter

        model = ViewModelProvider(this).get(CitysViewModel::class.java)
        model.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { citys ->
                this.citys = citys
                adapter.adnotify(citys)
            }


        buttonAdd = findViewById(R.id.buttonAddCity)
        buttonAdd.setOnClickListener() {
            model.addNew("Введите имя", 0)
        }
    }

    override fun citys(citys: MutableList<CityModel>) {
        this.citys = citys
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

}

