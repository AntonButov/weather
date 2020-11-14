package pro.butovanton.weather.Presentantion

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Observer.ObserverTemperature
import pro.butovanton.weather.Presentantion.Strategy.Strategy
import pro.butovanton.weather.R
import pro.butovanton.weather.Presentantion.ViewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    val model: MainViewModel by viewModels()
    lateinit var types : TypedArray
    lateinit var spinnerArrayAdapter : ArrayAdapter<String>

    var citySelect: Int =0
    var typeSelect: Int =0
    var seasonSelect: Int = 0
    var strategySelect: Int = 0
    var citys : List<City>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        types = resources.obtainTypedArray(R.array.size_city)

        buttonSytes.setOnClickListener() {
            view -> val intent = Intent(this, ActivityCitys:: class.java)
                        startActivity(intent)
        }

        spinnerCitySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                city: Int,
                id: Long
            ) {
                citySelect = city
                notifyCityTypeText()
                notifyTemper()
            }

        }

        spinnerSeasonSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                season: Int,
                id: Long
            ) {
            seasonSelect = season
            notifyTemper()
            }
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.strategy,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerStrategySelector.adapter = adapter
            }

        spinnerStrategySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                strategy: Int,
                id: Long
            ) {
               strategySelect = strategy
               notifyTemper()
            }
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.sesons,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerSeasonSelector.adapter = adapter
            }

             getData()

    }

    private fun getData() {
        model.getCitys()
            .subscribe { cityFromBD ->
                citys = cityFromBD
                notifyCityNames()
                notifyCityTypeText()
            }
    }

    private fun notifyCityNames() {
        val cityNames = mutableListOf<String>()
        citys?.forEach {city ->
            cityNames.add(city.name)
        }
                spinnerArrayAdapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cityNames)
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCitySelector.adapter = spinnerArrayAdapter
                spinnerCitySelector.setSelection(0)
            }

    private fun notifyCityTypeText() {
        citys?.let {
            textViewTypeCity.text = types.getText(it[citySelect].type).toString()
        }
    }

    private fun notifyTemper() {
        citys?.let {
          //  textViewTemper.setText(Strategy.calculate(strategySelect, t).toString() )
        }
    }
}




