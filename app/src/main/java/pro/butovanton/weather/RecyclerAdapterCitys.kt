package pro.butovanton.weather

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import android.widget.AdapterView.OnItemSelectedListener as OnItemSelectedListener1


class RecyclerAdapter(val activitySitys: ActivitySitys) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolderCitys>() {
    var citys: MutableList<City> = mutableListOf()
    val mInflater: LayoutInflater = LayoutInflater.from(activitySitys);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitys {
        val view = mInflater.inflate(R.layout.item, parent, false)
        return ViewHolderCitys(view, activitySitys)
    }

    override fun onBindViewHolder(holder: ViewHolderCitys, positionAdapter: Int) {
        holder.run {
            editTextViewItemName.setText(citys[positionAdapter].name)
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
                citys[positionAdapter] = Factory().Creat(position, citys[positionAdapter].name)
                save()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (citys == null) 0 else citys.size
    }

    fun adnotify(citys: MutableList<City>) {
        this.citys = citys
        notifyDataSetChanged()
    }

    fun save(){
        activitySitys.citys(citys)
    }

    class ViewHolderCitys(view: View, activitySitys: ActivitySitys) :
        RecyclerView.ViewHolder(view) {
        val editTextViewItemName: EditText
        val type: Spinner

        init {
            editTextViewItemName = view.findViewById(R.id.editTextItemName)
            type = view.findViewById(R.id.spinerItemType)

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









