package com.example.listvciew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var filmISaw = arrayOf("Retour vers le futur (1/2/3)",
            "Men In Black (1/2)",
            "La ligne verte",
            "Supercondriaque",
            "La bande à picsou le film",
            "Batman beyond",
            "Saw")
        var liste : ListView = findViewById(R.id.liste)
        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            filmISaw
        )

        liste.adapter = adapter
    }
}
