package pro.butovanton.weather

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import android.widget.LinearLayout
import io.reactivex.Single

class DialogRx(context: Context) {

    private val alertDialog = AlertDialog.Builder(context)
    private val input = EditText(context)

    init {
        alertDialog.setTitle("Редактирование")
        alertDialog.setMessage("Введите имя")
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        alertDialog.setView(input)
    }

    fun requestName(): Single<String> {
        return Single.create { cityName ->
            alertDialog.setPositiveButton("Записать",
                DialogInterface.OnClickListener { dialog, which ->
                    cityName.onSuccess(input.text.toString())
                })
            alertDialog.show()
        }
    }
}