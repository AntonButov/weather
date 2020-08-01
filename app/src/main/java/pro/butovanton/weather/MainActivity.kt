package pro.butovanton.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var buttonSytes : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSytes = findViewById(R.id.buttonSytes)
        buttonSytes.setOnClickListener() {
            view -> val intent = Intent(this, ActivitySitys:: class.java)
                        startActivity(intent)
        }
    }
}