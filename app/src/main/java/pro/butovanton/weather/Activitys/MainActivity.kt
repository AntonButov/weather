package pro.butovanton.weather.Activitys

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pro.butovanton.weather.Activitys.Observer.ObserverTemperature
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.MainViewModel

class MainActivity : AppCompatActivity(), ObserverTemperature {

    lateinit var buttonCytes : Button
    lateinit var model: MainViewModel
    lateinit var spinnerCity : Spinner
    lateinit var sesonsSpinner : Spinner
    lateinit var strategySpinner : Spinner
    var spinnerArray = listOf<String>("Тест")
    lateinit var textViewTypeCity: TextView
    lateinit var textViewTemper: TextView
    lateinit var types : TypedArray
    lateinit var spinnerArrayAdapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        types = resources.obtainTypedArray(R.array.size_city)

        textViewTemper = findViewById(R.id.textViewTemper)
        textViewTypeCity = findViewById(R.id.textViewTypeCity)

        buttonCytes = findViewById(R.id.buttonSytes)
        buttonCytes.setOnClickListener() {
            view -> val intent = Intent(this, ActivityCitys:: class.java)
                        startActivity(intent)
        }

        model = ViewModelProvider(this).get(MainViewModel::class.java)

        spinnerCity = findViewById(R.id.spinnerCity)
        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                city: Int,
                id: Long
            ) {
            model.setCityAndNotify(city)
            }

        }
        sesonsSpinner = findViewById(R.id.spinnerSeson)
        sesonsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                seson: Int,
                id: Long
            ) {
            model.setSesonAndNotify(seson)
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
                strategy: Int,
                id: Long
            ) {
                model.setStrategyAndNotify()
            }
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.sesons,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sesonsSpinner.adapter = adapter
            }

        model.registerObserverCityType().observe(this, Observer { type ->
            textViewTypeCity.text = types.getText(type).toString()
        })

        model.registerObserverTemperature().observe(this, Observer { t ->
            if (t == -255.toFloat())
                textViewTemper.setText("")
            else
                textViewTemper.setText(Strategy.calculate(strategySpinner.selectedItemPosition, t).toString())
        })

        model.registerObserver(this)
    }

    override fun onResume() {
        super.onResume()
        model.getCitysNames().observe(this, Observer { cityNames ->
            spinnerArrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cityNames)
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = spinnerArrayAdapter
            spinnerCity.setSelection(model.city!!)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        model.registerObserverTemperature().removeObservers(this)
        model.registerObserverCityType().removeObservers(this)
        model.unregisterObserver()
    }

    override fun observerNotify(message: String) {
        TODO("Not yet implemented")
    }

}

