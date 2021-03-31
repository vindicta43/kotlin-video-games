package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.ResponseGameDetails
import com.example.kotlin_video_games.database.Favorite
import com.example.kotlin_video_games.database.FavoriteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.squareup.picasso.Picasso
import java.io.IOException

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val gameID = bundle?.getInt("gameID")

        // favorite floating action button
        val fabFavorite = view.findViewById<FloatingActionButton>(R.id.fabFavorite)

        // favoriteList for getting favorites
        // and then change fab if the item contains/not
        val favoriteDatabase = FavoriteDatabase.getFavoriteDatabase(view.context)
        val favoriteList = favoriteDatabase?.favoriteDao()?.getFavorites()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.rawg.io/api/games/$gameID")
            .get()
            .addHeader("key", "1dce486d4f3f411c9d01d69e6849cf84")
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
                override fun onFailure(request: Request?, e: IOException?) {
                    e?.printStackTrace()
                }

                override fun onResponse(response: Response?) {
                    val body = response?.body()?.string()
                    val gson = Gson()

                    // game id
                    var ID: Int = 0

                    // ui elements
                    val bgImage = view.findViewById<ImageView>(R.id.ivDetail)
                    val gameName = view.findViewById<TextView>(R.id.tvGameName)
                    val gameReleased = view.findViewById<TextView>(R.id.tvReleaseDate)
                    val gameMetacritic = view.findViewById<TextView>(R.id.tvMetacriticRate)
                    val gameDesc = view.findViewById<TextView>(R.id.tvGameDesc)

                    val data = gson.fromJson(body, ResponseGameDetails::class.java)

                    runOnUiThread {
                        Picasso.get().load(data.backgroundImage).into(bgImage)
                        gameName.text = "Name: ${data.name}"
                        gameReleased.text = "Release Date: ${data.released}"
                        gameMetacritic.text = "Metacritic Score: ${data.metacritic.toString()}"
                        gameDesc.text = data.description

                        ID = data.id as Int


                        // if db contains the game
                        if (favoriteList != null && favoriteList.contains(Favorite(ID))) {
                            fabFavorite.setImageResource(R.drawable.ic_favorite)
                        }
                        else{
                            fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                        }
                    }

                    // fab onclick for adding or removing from favorites
                    fabFavorite.setOnClickListener {
                        // if db contains the game
                        if (favoriteList != null && favoriteList.contains(Favorite(ID))) {
                            favoriteDatabase.favoriteDao().delete(Favorite(ID))
                            fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                            Toast.makeText(view.context, "Game removed from favorites", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            favoriteDatabase?.favoriteDao()?.insert(Favorite(ID))
                            fabFavorite.setImageResource(R.drawable.ic_favorite)
                            Toast.makeText(view.context, "Game added to favorites", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        )

    }
}