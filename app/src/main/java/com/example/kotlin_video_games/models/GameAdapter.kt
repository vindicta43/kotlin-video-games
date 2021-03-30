package com.example.kotlin_video_games.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_video_games.R
import com.squareup.picasso.Picasso

class GameAdapter(var ctx: Context, var resorces:Int, var items:List<ModelGameItem?>):ArrayAdapter<ModelGameItem>(ctx, resorces, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resorces, null, true)

        val gameImage: ImageView = view.findViewById(R.id.gameItemImage)
        val gameName : TextView = view.findViewById(R.id.gameItemName)
        val gameRating: TextView = view.findViewById(R.id.gameItemRating)
        val gameRelease: TextView = view.findViewById(R.id.gameItemRelease)

        // adapter sections will be updated
        var myItem: ModelGameItem? = items[position]
        Picasso.get().load(items[position]?.backgroundImage).into(gameImage)
        gameName.text = myItem?.name
        gameRating.text = myItem?.rating
        gameRelease.text = myItem?.released

        return view
    }
}