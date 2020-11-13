package pro.butovanton.weather.Presentantion

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import pro.butovanton.weather.DialogRx
import pro.butovanton.weather.Factory.CityModel
import pro.butovanton.weather.R


class AdapterCitys(val context: Context, val returnData: notifyCitys) :
    RecyclerView.Adapter<AdapterCitys.ViewHolderCitys>() {

    var citys: MutableList<CityModel> = mutableListOf()
    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitys {
        val view = mInflater.inflate(R.layout.item, parent, false)
        return ViewHolderCitys(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCitys, positionAdapter: Int) {
        holder.run {
            nameTextView.setText(citys[positionAdapter].name)
            nameTextView.setOnClickListener {
               DialogRx(context).requestName()
                   .subscribe { newName ->

                   }
            }

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
                    //     returnData.update(citys[positionAdapter], positionAdapter)
            }
        }
        holder.buttonDel.setOnClickListener {
            returnData.delete(city = citys[positionAdapter].name)
            citys.removeAt(positionAdapter)
            notifyDataSetChanged()
        }
        holder.buttonTemper.setOnClickListener {
            returnData.callTemperActivity(citys[positionAdapter].name)
        }
    }

    override fun getItemCount(): Int {
        return if (citys == null) 0 else citys.size
    }

    fun setData(citys: MutableList<CityModel>) {
        this.citys = citys
        notifyDataSetChanged()
    }

    class ViewHolderCitys(view: View) :
        RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val type: Spinner
        val buttonDel : Button
        val buttonTemper : Button

        init {
            nameTextView = view.findViewById(R.id.name_TV)
            type = view.findViewById(R.id.spinerItemType)
            buttonDel = view.findViewById(R.id.buttonDel)
            buttonTemper = view.findViewById(R.id.buttonTemper)
            ArrayAdapter.createFromResource( view.context, R.array.size_city,
                android.R.layout.simple_spinner_item
            )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    type.adapter = adapter
                }
        }
    }
}









