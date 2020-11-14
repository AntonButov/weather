package pro.butovanton.weather.Presentantion

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import pro.butovanton.weather.Presentantion.View.ActivityTemper
import pro.butovanton.weather.R

class AdapterTemper(val context : ActivityTemper, val temperatures: MutableList<Int?>) :
    RecyclerView.Adapter<AdapterTemper.ViewHolderTemper>() {
    val mInflater: LayoutInflater = LayoutInflater.from(context);
    val months: TypedArray = context.resources.obtainTypedArray(R.array.months)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTemper {
        val view = mInflater.inflate(R.layout.item_temperatures, parent, false)
        return ViewHolderTemper(
            view,
            context
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolderTemper,
        positionAdapter: Int
    ) {
        holder.run {
            textViewMonth.setText(months.getString(positionAdapter))
            val t = temperatures[positionAdapter]
            if (t != null)
                   editTextTemper.setText("" + t)
            editTextTemper.setOnTouchListener(View.OnTouchListener { v, event ->
                v.isFocusable = true
                v.isFocusableInTouchMode = true
                false
            })
            editTextTemper.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    temperatures[positionAdapter] = s.toString().toInt()
                    save()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

            })
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

        fun save() {
        context.temperatureSave(temperatures)
        }

    class ViewHolderTemper(view: View, context: Context) :
        RecyclerView.ViewHolder(view) {
        val editTextTemper: EditText
        val textViewMonth: TextView

        init {
            editTextTemper = view.findViewById(R.id.editTextItemTemper)
            textViewMonth = view.findViewById(R.id.textViewMonth)
        }
    }
}









