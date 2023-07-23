package com.example.teeth_color

import android.Manifest
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : ComponentActivity() {

    private lateinit var imageView: ImageView
    private lateinit var captureButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image_view)
        captureButton = findViewById(R.id.capture_button)
        setPermission()
        captureButton.setOnClickListener {
            checkCameraPermission("촬영!!!!!!")
        }
    }



    private fun checkCameraPermission(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

    private fun setPermission() {
        val permission = object : PermissionListener{

            override fun onPermissionGranted() {
                Toast.makeText(this@MainActivity, "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity, "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.create()
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해 주세요")
            .setDeniedMessage("권한을 거부하셨습니다.")
            .setPermissions(Manifest.permission.CAMERA)
            .check()

    }
}
