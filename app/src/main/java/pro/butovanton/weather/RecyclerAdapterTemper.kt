package pro.butovanton.weather

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterTemper(val context : Context, val city : Int, val citys: List<City>) :
    RecyclerView.Adapter<RecyclerAdapterTemper.ViewHolderTemper>() {
    val mInflater: LayoutInflater = LayoutInflater.from(context);
    val months: TypedArray = context.resources.obtainTypedArray(R.array.months)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTemper {
        val view = mInflater.inflate(R.layout.item_temperatures, parent, false)
        return ViewHolderTemper(view, context)
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapterTemper.ViewHolderTemper,
        positionAdapter: Int
    ) {
        holder.run {
            textViewMonth.setText(months.getText(positionAdapter))
            editTextTemper.setText("" + citys[city].temperature[positionAdapter])
         //   editTextTemper.addTextChangedListener(object : TextWatcher {
       //         override fun afterTextChanged(s: Editable?) {
      //              citys[city].temperature[positionAdapter] = s.toString().toInt()
     //               save()
     //           }

     //           override fun beforeTextChanged(
   //                 s: CharSequence?,
   //                 start: Int,
    //                count: Int,
   //                 after: Int
   //             ) {
   //             }

  //              override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

   //             }

   //         })
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

        fun save() {
      //      context.save(citys)
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









