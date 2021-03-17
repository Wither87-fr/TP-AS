package fr.unilim.iut.changer.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.change)
        var compteur = 1
        button.setOnClickListener {
            var id = this.resources.getIdentifier("umbreon" + (compteur % 2).toString(), "drawable", this.packageName)
            val img : ImageView = findViewById(R.id.umbreon)
            img.setImageResource(id)
            compteur++
        }
    }
}