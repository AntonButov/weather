package pro.butovanton.weather.Activitys

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import pro.butovanton.weather.Factory.City
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.R


class RecyclerAdapterCitys(val activitySitys: ActivityCitys) :
    RecyclerView.Adapter<RecyclerAdapterCitys.ViewHolderCitys>() {
    var citys: MutableList<CityModel> = mutableListOf()
    val mInflater: LayoutInflater = LayoutInflater.from(activitySitys);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitys {
        val view = mInflater.inflate(R.layout.item, parent, false)
        return ViewHolderCitys(
            view,
            activitySitys
        )
    }

    override fun onBindViewHolder(holder: ViewHolderCitys, positionAdapter: Int) {
        holder.run {
            editTextViewItemName.setText(citys[positionAdapter].name)
            editTextViewItemName.setOnTouchListener(OnTouchListener { v, event ->
                v.isFocusable = true
                v.isFocusableInTouchMode = true
                false
            })
            editTextViewItemName.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    citys[positionAdapter].name = s.toString()
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
            type.setSelection(citys[positionAdapter].type)
        }
        holder.type.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                citys[positionAdapter].type = position
                save()
            }
        }
        holder.buttonDel.setOnClickListener {
            citys.removeAt(positionAdapter)
            notifyDataSetChanged()
            save()
        }
        holder.buttonTemper.setOnClickListener {
            activitySitys.temper(positionAdapter)
        }
    }

    override fun getItemCount(): Int {
        return if (citys == null) 0 else citys.size
    }

    fun adnotify(citys: MutableList<CityModel>) {
        this.citys = citys
        notifyDataSetChanged()
    }

    fun save(){
        activitySitys.citys(citys)
    }

    class ViewHolderCitys(view: View, activitySitys: ActivityCitys) :
        RecyclerView.ViewHolder(view) {
        val editTextViewItemName: EditText
        val type: Spinner
        val buttonDel : Button
        val buttonTemper : Button

        init {
            editTextViewItemName = view.findViewById(R.id.editTextName)
            type = view.findViewById(R.id.spinerItemType)
            buttonDel = view.findViewById(R.id.buttonDel)
            buttonTemper = view.findViewById(R.id.buttonTemper)
            ArrayAdapter.createFromResource(
                activitySitys,
                R.array.size_city,
                android.R.layout.simple_spinner_item
            )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    type.adapter = adapter
                }
        }


    }

}









