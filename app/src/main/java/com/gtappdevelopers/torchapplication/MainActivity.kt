package com.gtappdevelopers.torchapplication

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var torchIV: ImageView
    lateinit var lightIV: ImageView
    lateinit var torchTV: TextView
    private var cameraManager: CameraManager? = null
    private var getCameraID: String? = null
    private var torchStatus: Boolean = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        torchIV = findViewById(R.id.idIVTorch)
        lightIV = findViewById(R.id.idIVLight)
        torchTV = findViewById(R.id.idTVSubHeading)

        cameraManager = applicationContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            getCameraID = cameraManager!!.cameraIdList[0]
        } catch (e: Exception) {
            e.printStackTrace()
        }

        torchIV.setOnClickListener {
            torchStatus = !torchStatus
            if (torchStatus) {
                // torch is ON.
                lightIV.visibility = View.VISIBLE
                torchTV.text = "Press on Torch to Trun it OFF"
                try {
                    // true sets the torch in ON mode
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        cameraManager!!.setTorchMode(getCameraID!!, true)
                    }
                    Toast.makeText(this@MainActivity, "Flashlight is turned ON", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                // torch is OFF
                lightIV.visibility = View.GONE
                torchTV.text = "Press on Torch to Trun it ON"
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        cameraManager!!.setTorchMode(getCameraID!!, false)
                    }

                    Toast.makeText(
                        this@MainActivity,
                        "Flashlight is turned OFF",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}