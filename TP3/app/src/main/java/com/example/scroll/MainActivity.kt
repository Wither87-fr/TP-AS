package com.example.scroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.StringBuilder
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txt : TextView = findViewById(R.id.txt)
        var txt_texte : String = txt.text.toString()
        var i = 0
        while (i < 500) {
            txt_texte = StringBuilder().append(txt_texte).append(PI * i).toString()
            i++
        }
        txt_texte = StringBuilder().append(txt_texte).append("-FIN").toString()
        txt.text = txt_texte
    }
}
