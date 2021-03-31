package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.ResponseGameDetails
import com.example.kotlin_video_games.database.FavoriteDatabase
import com.example.kotlin_video_games.models.GameAdapter
import com.example.kotlin_video_games.models.ModelGameItem
import com.google.gson.Gson
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException

class FavoriteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listView = view.findViewById<ListView>(R.id.listViewFavorite)
        var list = arrayListOf<ModelGameItem>()

        fillFavoriteList()
    }

    private fun fillFavoriteList() {
        // db connection
        val favoriteDatabase = view?.context?.let { FavoriteDatabase.getFavoriteDatabase(it) }
        val favoriteList = favoriteDatabase?.favoriteDao()?.getFavorites()

        // ui elements
        val gameNotFoundFavoriteList = view?.findViewById<TextView>(R.id.tvGameNotFoundFavoriteList)
        val listView = view?.findViewById<ListView>(R.id.listViewFavorite)
        var list = arrayListOf<ModelGameItem?>()

        // connection and loop
        val client = OkHttpClient()

        if (favoriteList?.isEmpty() == true) {
            gameNotFoundFavoriteList?.visibility = View.VISIBLE
        } else {
            gameNotFoundFavoriteList?.visibility = View.INVISIBLE

            if (favoriteList != null) {
                for (i in favoriteList) {
                    val request = Request.Builder()
                        .url("https://api.rawg.io/api/games/${i.gameID}")
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

                            val data = gson.fromJson(
                                body, ResponseGameDetails::class.java
                            )

                            // id read from database
                            // internet connection for getting current id game
                            val gameId: Int = data.id as Int
                            val gameName: String? = data.name
                            val gameRating: Double? = data.rating
                            val gameReleased: String? = data.released
                            val gameImgSource: String? = data.backgroundImage

                            list.add(
                                ModelGameItem(
                                    gameId,
                                    gameName,
                                    gameRating.toString(),
                                    gameReleased,
                                    gameImgSource
                                )
                            )

                            runOnUiThread {
                                listView?.adapter =
                                    view?.let { GameAdapter(it.context, R.layout.game_item, list) }
                            }


                        }
                    })
                }
            }
        }

        // listview click event
        listView?.setOnItemClickListener { parent, view, position, id ->
            val bundle = Bundle()
            val gameID = list[position]?.id as Int
            bundle.putInt("gameID", gameID)
            val detailFragment = DetailFragment()
            detailFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, detailFragment)
                commit()
            }
        }
    }
}

//  fill later
//        listView.adapter =
//            activity?.let { GameAdapter(it.applicationContext, R.layout.game_item, list) }
//
// listView.setOnItemClickListener()