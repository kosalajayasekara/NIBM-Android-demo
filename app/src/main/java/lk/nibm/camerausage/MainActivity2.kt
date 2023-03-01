package lk.nibm.camerausage

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog


class MainActivity2 : AppCompatActivity() {

    lateinit var btnLoading : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnLoading = findViewById(R.id.btnLoading)

        btnLoading.setOnClickListener{
            val pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
            pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
            pDialog.titleText = "Loading"
            pDialog.setCancelable(false)
            pDialog.show()
        }
    }
}