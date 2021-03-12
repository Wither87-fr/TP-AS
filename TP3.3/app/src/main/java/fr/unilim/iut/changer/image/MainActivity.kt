package fr.unilim.iut.changer.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.change)
        var compteur = 0
        button.setOnClickListener {
            Toast.makeText(this, "Bouton cliqué $compteur fois", Toast.LENGTH_SHORT).show()
            compteur++
        }
    }
}