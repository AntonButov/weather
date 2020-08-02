package pro.butovanton.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(val activitySitys: ActivitySitys) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolderCitys>() {
    var citys: List<City> = mutableListOf()
    val mInflater: LayoutInflater = LayoutInflater.from(activitySitys);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitys {
        val view = mInflater.inflate(R.layout.item, parent, false)
        return ViewHolderCitys(view, activitySitys)
    }

    override fun onBindViewHolder(holder: ViewHolderCitys, position: Int) {
        holder.textViewItemName.setText(citys[position].name)

    }

    override fun getItemCount(): Int {
        return if (citys == null) 0 else citys.size
    }

    fun adnotify(citys: List<City>?) {
        this.citys = citys!!
        notifyDataSetChanged()
    }

    class ViewHolderCitys(view: View, activitySitys: ActivitySitys) :
        RecyclerView.ViewHolder(view) {
        val textViewItemName: TextView
        val type: Spinner

        init {
            textViewItemName = view.findViewById(R.id.textItemName)
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






