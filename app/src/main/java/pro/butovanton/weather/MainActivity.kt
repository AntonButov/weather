package pro.butovanton.weather

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var buttonCytes : Button
    lateinit var model: MainViewModel
    lateinit var spinnerCity : Spinner
    lateinit var sesonsSpinner : Spinner
    lateinit var strategySpinner : Spinner
    lateinit var spinnerArray: List<String>
    lateinit var textViewTypeCity: TextView
    lateinit var textViewTemper: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCytes = findViewById(R.id.buttonSytes)
        buttonCytes.setOnClickListener() {
            view -> val intent = Intent(this, ActivitySitys:: class.java)
                        startActivity(intent)
        }

        model = ViewModelProvider(this).get(MainViewModel::class.java)

        spinnerArray = model.getCitysNames()
        val spinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity = findViewById(R.id.spinnerCity)
        spinnerCity.adapter = spinnerArrayAdapter
        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
              model.city = position
              model.notifyUI()
            }

        }
        sesonsSpinner = findViewById(R.id.spinnerSeson)
        sesonsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            model.seson = position
            model.notifyUI()
            }
        }
        strategySpinner = findViewById(R.id.spinnerStrategy)
        ArrayAdapter.createFromResource(
            this,
            R.array.strategy,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                strategySpinner.adapter = adapter
            }

        strategySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                model.strategy = position
                model.notifyUI()
            }
        }
        val types = resources.obtainTypedArray(R.array.size_city)
        ArrayAdapter.createFromResource(
            this,
            R.array.sesons,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sesonsSpinner.adapter = adapter
            }
        textViewTypeCity = findViewById(R.id.textViewTypeCity)
        textViewTemper = findViewById(R.id.textViewTemper)

        model.getInfo().observe(this, Observer {
            uiInfo -> textViewTypeCity.setText(types.getText(uiInfo.type))
                     if (uiInfo.temepr == - 255)
                         textViewTemper.setText("")
                    else
                        textViewTemper.setText(uiInfo.temepr.toString())
        })
    }

    override fun onResume() {
        super.onResume()
        spinnerArray = model.getCitysNames()
    }

    override fun onDestroy() {
        super.onDestroy()
        model.getInfo().removeObservers(this)
    }

}

