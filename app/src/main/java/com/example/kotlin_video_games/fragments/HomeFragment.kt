package com.example.kotlin_video_games.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.models.CustomViewPager
import com.example.kotlin_video_games.models.GameAdapter
import com.example.kotlin_video_games.models.ModelGameItem
import com.example.kotlin_video_games.models.ViewPagerAdapter
import com.google.gson.Gson
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException


// https://api.rawg.io/api/games?page=$pageNum

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillViewPager()
        fillRecyclerView()
    }

    fun fillViewPager() {
        // viewpager click event doesn't working
        val myPager = view?.findViewById<CustomViewPager>(R.id.viewPager)
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://rawg-video-games-database.p.rapidapi.com/games")
            .get()
            .addHeader("x-rapidapi-key", "20b281e554msh8b74305ebe8a24ap162013jsn79f1204a2ed4")
            .addHeader("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(response: Response?) {
                var body = response?.body()?.string()
                var gson = Gson()

                val data = gson.fromJson(
                    body,
                    com.example.kotlin_video_games.json_game_list.Response::class.java
                )
                var gameImageList = arrayListOf<String>()
                var gameNameList = arrayListOf<String>()
                for (i in 0..2) {
                    gameImageList.add(data.results[i].backgroundImage)
                    gameNameList.add("#${i + 1} ${data.results[i].name}")
                }
                var pagerAdapter = ViewPagerAdapter(gameImageList, gameNameList, view?.context)

                runOnUiThread {
                    myPager?.adapter = pagerAdapter
                }
            }
        })
        myPager?.setOnClickListener {
            val detailFragment = DetailFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, detailFragment)
                commit()
            }
        }
    }

    fun fillRecyclerView() {
        var recyclerView = view?.findViewById<ListView>(R.id.listView)
        var list = arrayListOf<ModelGameItem>()
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.rawg.io/api/games")
            .get()
            .addHeader("key", "1dce486d4f3f411c9d01d69e6849cf84")
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(response: Response?) {
                var body = response?.body()?.string()
                var gson = Gson()

                val data = gson.fromJson(
                    body,
                    com.example.kotlin_video_games.json_game_list.Response::class.java
                )
                for (i in 3 until data.results.size - 1) {
                    val gameName = data.results[i].name
                    val gameRating = data.results[i].rating
                    val gameReleased = data.results[i].released
                    val gameImgSource = data.results[i].backgroundImage

                    var listItem =
                        ModelGameItem(gameName, gameRating.toString(), gameReleased, gameImgSource)
                    list.add(listItem)
                }

                runOnUiThread {
                    recyclerView?.adapter =
                        view?.let { GameAdapter(it.context, R.layout.game_item, list) }

                }
            }
        })
        recyclerView?.setOnItemClickListener { parent, view, position, id ->
            val detailFragment = DetailFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, detailFragment)
                commit()
            }
        }
    }
}

fun Fragment?.runOnUiThread(action: () -> Unit) {
    this ?: return
    if (!isAdded) return // Fragment not attached to an Activity
    activity?.runOnUiThread(action)
}


