package pro.butovanton.weather.Activitys

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import pro.butovanton.weather.Activitys.Observer.ObserverTemperature
import pro.butovanton.weather.Activitys.Strategy.Strategy
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.MainViewModel

class MainActivity : AppCompatActivity(), ObserverTemperature {

    lateinit var model: MainViewModel
    lateinit var types : TypedArray
    lateinit var spinnerArrayAdapter : ArrayAdapter<String>
    var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        types = resources.obtainTypedArray(R.array.size_city)

        buttonSytes.setOnClickListener() {
            view -> val intent = Intent(this, ActivityCitys:: class.java)
                        startActivity(intent)
        }

        model = ViewModelProvider(this).get(MainViewModel::class.java)

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

        spinnerSeson.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        ArrayAdapter.createFromResource(
            this,
            R.array.strategy,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerStrategy.adapter = adapter
            }

        spinnerStrategy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                spinnerSeson.adapter = adapter
            }

        model.registerObserverCityType().observe(this, Observer { type ->
            textViewTypeCity.text = types.getText(type).toString()
        })

        model.registerObserverTemperature().observe(this, Observer { t ->
            if (t == -255.toFloat())
                textViewTemper.setText("")
            else
                textViewTemper.setText(Strategy.calculate(spinnerStrategy.selectedItemPosition, t).toString())
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

     if (!message.equals("")) {
         val snackbar = Snackbar.make(
             mainConstraint, message,
             Snackbar.LENGTH_LONG
         )
         snackbar.show()
         message = ""
     }
    }

    override fun onDestroy() {
        super.onDestroy()
        model.registerObserverTemperature().removeObservers(this)
        model.registerObserverCityType().removeObservers(this)
        model.unregisterObserver()
    }

    override fun observerNotify(message: String) {
        this.message = message
    }
}

