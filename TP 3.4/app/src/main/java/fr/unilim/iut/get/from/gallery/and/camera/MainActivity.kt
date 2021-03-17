package fr.unilim.iut.get.from.gallery.and.camera

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val REQUEST_CODE = 200
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(askForPermission()) {
            val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_TEXT)
            val imageView = findViewById<ImageView>(R.id.chosen_photo)
            if (uri != null) imageView.setImageURI(uri)
        }

        val btnGallerie : Button = findViewById(R.id.chose_photo)
        btnGallerie.setOnClickListener {
            val intent = Intent(this@MainActivity, GallerieActivity::class.java)
            startActivity(intent)
        }
        val btnCamera : Button = findViewById(R.id.take_photo)
        btnCamera.setOnClickListener {
            capturePhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            val imageView = findViewById<ImageView>(R.id.chosen_photo)
            imageView.setImageBitmap(data.extras!!.get("data") as Bitmap)
        }
    }


    fun isPermissionAllowed() : Boolean {
        Boolean

        // changé par l'IDE
        return !(ContextCompat.checkSelfPermission( this,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    }

    fun askForPermission() : Boolean {
        if(!isPermissionAllowed()) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this as Activity, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this as Activity, Manifest.permission.CAMERA) ) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(this as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA), REQUEST_CODE)
            }
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)
                } else
                {
                    askForPermission()
                }
                return
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        val positiveButton = AlertDialog.Builder(this)
            .setTitle("Permission denied")
            .setMessage("Permission denied, please allow from app settings.")
            .setPositiveButton(
                "App Settings",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                })
            .setNegativeButton("Cancel", null)
            .show()
    }

    fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }
}