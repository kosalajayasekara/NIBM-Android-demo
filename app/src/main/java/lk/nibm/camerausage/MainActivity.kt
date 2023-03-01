package lk.nibm.camerausage

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    lateinit var captureImage : ImageView
    lateinit var btnCapture : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captureImage = findViewById(R.id.imgCapture)
        btnCapture = findViewById(R.id.btnCapture)

        checkPermission()

        btnCapture.setOnClickListener{
            capturePhoto()
        }
    }

    fun checkPermission(){
//        if(ActivityCompat.shouldShowRequestPermissionRationale
//                (this@MainActivity,
//                android.Manifest.permission.CAMERA)){
//            ActivityCompat.requestPermissions(this@MainActivity,
//                arrayOf(android.Manifest.permission.CAMERA),1)
//        }
        ActivityCompat.requestPermissions(this@MainActivity,
            arrayOf(android.Manifest.permission.CAMERA),1)

    }

    fun capturePhoto(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

      //  val imageData = data?.extras?.get("data") as Bitmap
       // Log.e("Image Data", imageData.toString())

        if (resultCode == Activity.RESULT_OK){
            val imageData = data?.extras?.get("data") as Bitmap
            captureImage.setImageBitmap(imageData)
        }
    }

}