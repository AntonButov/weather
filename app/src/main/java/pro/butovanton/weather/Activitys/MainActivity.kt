package pro.butovanton.weather.Activitys

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import pro.butovanton.weather.R
import pro.butovanton.weather.ViewModels.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var buttonCytes : Button
    lateinit var model: MainViewModel
    lateinit var spinnerCity : Spinner
    lateinit var sesonsSpinner : Spinner
    lateinit var strategySpinner : Spinner
    var spinnerArray = listOf<String>()
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

         model.getCitysNames()
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe { cityNames ->
                 spinnerArray = cityNames }
             .dispose()
            spinnerArrayAdapter =
                     ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)
                 spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinnerCity = findViewById(R.id.spinnerCity)
        spinnerCity.adapter = spinnerArrayAdapter
        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                city: Int,
                id: Long
            ) {
             onResume()
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
            model.seson = seson
            showTemper()
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
                model.strategy = strategy
                showTemper()
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

    }

    fun showTemper() {
       var t = model.getTemper(strategySpinner.selectedItemPosition)
        if (t == -255.toFloat())
            textViewTemper.setText("")
        else
            textViewTemper.setText(t.toString())
    }

    fun showTypeCity(city : Int) {
        if (city >= 0)
            model.getCityType(city)
                   .subscribe { type -> textViewTypeCity.setText(types.getText(type))}
                   .dispose()
    }

    override fun onResume() {
        super.onResume()

        model.getCitysNames()
            .subscribe { cityNames -> spinnerArray = cityNames }
            .dispose()
        showTypeCity(spinnerCity.selectedItemPosition)
        showTemper()
    }


}

