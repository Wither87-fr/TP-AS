package fr.unilim.iut.bibliotheque.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fr.unilim.iut.bibliotheque.R

class LivreAdapter(private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val email: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, name) {

    @SuppressLint("InflateParams", "ViewHolder", "SetTextI18n")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val isbnText = rowView.findViewById(R.id.textViewISBN) as TextView
        val titreText = rowView.findViewById(R.id.textViewTitre) as TextView

        idText.text = "Id: ${id[position]}"
        isbnText.text = "ISBN: ${name[position]}"
        titreText.text = "Titre: ${email[position]}"
        return rowView
    }
}