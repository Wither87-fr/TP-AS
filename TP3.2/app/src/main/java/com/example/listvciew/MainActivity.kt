package com.example.listvciew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var filmISaw = ArrayList<Film>()
        filmISaw.add(Film("Retour vers le futur (1,2,3)", "back_to_the_future"))
        filmISaw.add(Film("Men in black (1,2)", "mib"))
        filmISaw.add(Film("La ligne verte", "green_mile"))
        filmISaw.add(Film("Supercondriaque", "supercondriaque"))
        filmISaw.add(Film("La bande à picsou le film", "picsou"))
        filmISaw.add(Film("Batman Beyond", "batman"))
        filmISaw.add(Film("Saw", "saw"))
        var liste : ListView = findViewById(R.id.liste)
        val adapter : FilmAdaptater = FilmAdaptater(filmISaw, this)

        liste.adapter = adapter

        liste.setOnItemClickListener {
            parent, view, position, id ->
            Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()

        }
    }
}
