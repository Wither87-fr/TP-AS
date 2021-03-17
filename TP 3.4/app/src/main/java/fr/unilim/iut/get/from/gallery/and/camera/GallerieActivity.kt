package fr.unilim.iut.get.from.gallery.and.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class GallerieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallerie)
        openGalleryForImage()
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var imageView : ImageView = findViewById(R.id.imageView2)
        imageView?.setImageURI(data?.data)
        val intent = Intent(this@GallerieActivity, MainActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, data?.data)
        startActivity(intent)
    }
}