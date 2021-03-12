package com.example.listvciew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class FilmAdaptater (items : ArrayList<Film>, ctx : Context) : ArrayAdapter<Film>(ctx, R.layout.list_item_recipe, items) {
    private class FilmItemViewHolder {
        internal var image : ImageView? = null
        internal var title : TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val viewHolder : FilmItemViewHolder
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_item_recipe, parent, false)
            viewHolder = FilmItemViewHolder()
            viewHolder.title = view!!.findViewById(R.id.titre) as TextView
            viewHolder.image = view.findViewById(R.id.image) as ImageView
        } else
        {
            viewHolder = view.tag as FilmItemViewHolder
        }
        val film = getItem(position)
        viewHolder.title!!.text = film!!.titre
        var id = context.resources.getIdentifier(
            film!!.imgName, "drawable", context.packageName
        )
        viewHolder.image!!.setImageResource(id)
        viewHolder.image!!.setOnClickListener {
            Toast.makeText(context, "Clicked Image of " + film!!.titre, Toast.LENGTH_SHORT).show()
        }

        view.tag = viewHolder

        return view
    }
}