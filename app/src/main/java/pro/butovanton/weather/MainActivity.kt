package pro.butovanton.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var buttonCytes : Button
    lateinit var model: MainViewModel
    lateinit var spinner : Spinner
    lateinit var spinnerArray: List<String>

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
        spinner = findViewById(R.id.spinnerCity)
        spinner.adapter = spinnerArrayAdapter
    }

    override fun onResume() {
        super.onResume()
        spinnerArray = model.getCitysNames()
    }
}