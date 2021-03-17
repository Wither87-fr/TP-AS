package fr.unilim.iut.get.from.gallery.and.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GallerieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallerie)
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)

    }
}