package lk.nibm.camerausage

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationActivity : AppCompatActivity() {

    lateinit var fusedLocation : FusedLocationProviderClient

    lateinit var  btnAccessLocation : Button

    var isPermissionGranted : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        checkPermission()

        btnAccessLocation = findViewById(R.id.btngetLocation)

        btnAccessLocation.setOnClickListener{
            getLocation()
        }
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED){
            isPermissionGranted = true
        }else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1
            )
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        if (isPermissionGranted) {
            Log.e("Location", "Permission Granted")
            val locationResult = fusedLocation.lastLocation
            locationResult.addOnCompleteListener(this) { location ->
                Log.e("Location", "Received Location")
                if (location.isSuccessful) {
                    Log.e("Location", "Location Success")
                    var userLocation = location.result
                    Log.e("Location", "User Location "+ userLocation)
                    if (userLocation != null) {
                        Toast.makeText(
                            applicationContext,
                            "User Location Longitude :" + userLocation.longitude + " Latitude : " + userLocation.latitude,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        isPermissionGranted = false

        when(requestCode){ 1 ->{
            if(grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    isPermissionGranted = true
                }
            }else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}