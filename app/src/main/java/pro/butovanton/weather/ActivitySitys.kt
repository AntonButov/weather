package pro.butovanton.weather

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivitySitys : AppCompatActivity(), notifyCitys  {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    lateinit var lm : LinearLayoutManager
    lateinit var buttonAdd: Button
    lateinit var citys : MutableList<City>
    val factory = Factory()
    lateinit var model : CitysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sytes)

        recyclerView = findViewById(R.id.recicler)
        adapter = RecyclerAdapter(this)
        lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.adapter = adapter

        model = ViewModelProvider(this).get(CitysViewModel::class.java)
        citys = model.getCitys()
        adapter.adnotify(citys)

        buttonAdd = findViewById(R.id.buttonTemper)
        buttonAdd.setOnClickListener() {
            citys.add(factory.Creat(0, "введите имя"))
            adapter.adnotify(citys)
            save()
        }
    }

    fun save() {
        model.setCitys(citys)
    }

     override fun citys(citys: List<City>) {
         this.citys = citys as MutableList<City>
         save()
     }

 }


