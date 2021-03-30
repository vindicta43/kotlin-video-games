package com.example.kotlin_video_games.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
        fillViewPager("https://api.rawg.io/api/games", 0)
        fillListView("https://api.rawg.io/api/games", 0)
        searchGame()
    }

    fun fillViewPager(url: String, count: Int) {
        val tvGameNotFound = view?.findViewById<TextView>(R.id.tvGameNotFound)
        val myPager = view?.findViewById<CustomViewPager>(R.id.viewPager)
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
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
                // when no game is found
                if (data.count == 0 || data.results == null) {
                    runOnUiThread {
                        myPager?.visibility = View.INVISIBLE
                    }
                }
                // searching situation
                else if (count > 0) {
                    runOnUiThread {
                        tvGameNotFound?.visibility = View.INVISIBLE
                        myPager?.visibility = View.INVISIBLE
                    }
                }
                // edittext is empty
                else {
                    val gameImageList = arrayListOf<String?>()
                    val gameNameList = arrayListOf<String?>()
                    for (i in 0..2) {
                        gameImageList.add(data.results[i].backgroundImage)
                        gameNameList.add("#${i + 1} ${data.results[i].name}")
                    }
                    val pagerAdapter = ViewPagerAdapter(gameImageList, gameNameList, view?.context)

                    runOnUiThread {
                        myPager?.visibility = View.INVISIBLE
                        myPager?.visibility = View.VISIBLE
                        myPager?.adapter = pagerAdapter
                    }
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

    fun fillListView(url: String, count: Int) {
        val tvGameNotFound = view?.findViewById<TextView>(R.id.tvGameNotFound)
        var listView = view?.findViewById<ListView>(R.id.listView)
        var list = arrayListOf<ModelGameItem?>()
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
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
                // when no game is found
                if (data.count == 0 || data.results == null) {
                    runOnUiThread {
                        listView?.visibility = View.INVISIBLE
                        tvGameNotFound?.visibility = View.VISIBLE
                    }
                }
                // searching situation
                else if (count > 0){
                    for (i in 0 until data.results.size) {
                        val gameName: String? = data.results[i].name
                        val gameRating: Double = data.results[i].rating
                        val gameReleased: String? = data.results[i].released
                        val gameImgSource: String? = data.results[i].backgroundImage

                        list.add(ModelGameItem(gameName, gameRating.toString(), gameReleased, gameImgSource))
                    }

                    runOnUiThread {
                        listView?.adapter =
                            view?.let { GameAdapter(it.context, R.layout.game_item, list) }
                        tvGameNotFound?.visibility = View.INVISIBLE
                        listView?.visibility = View.VISIBLE
                    }
                }
                // edittext is empty
                else {
                    for (i in 3 until data.results.size) {
                        val gameName: String? = data.results[i].name
                        val gameRating: Double = data.results[i].rating
                        val gameReleased: String? = data.results[i].released
                        val gameImgSource: String? = data.results[i].backgroundImage

                        list.add(ModelGameItem(gameName, gameRating.toString(), gameReleased, gameImgSource))
                    }

                    runOnUiThread {
                        listView?.adapter =
                            view?.let { GameAdapter(it.context, R.layout.game_item, list) }
                        tvGameNotFound?.visibility = View.INVISIBLE
                    }
                }
            }
        })
        listView?.setOnItemClickListener { parent, view, position, id ->
            val detailFragment = DetailFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, detailFragment)
                commit()
            }
        }
    }

    fun searchGame() {
        val etSearch = view?.findViewById<EditText>(R.id.etSearch)

        etSearch?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://api.rawg.io/api/games?search=$s")
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

                        // search query after equals and bigger than 3
                        if (count >= 3) {
                            if (data.results != null) {
                                runOnUiThread {
                                    fillViewPager("https://api.rawg.io/api/games?search=$s", count)
                                    fillListView("https://api.rawg.io/api/games?search=$s", count)
                                }
                            }
                        }
                        // when edittext is empty default ratings will be listed
                        if(count == 0) {
                            runOnUiThread {
                                fillViewPager("https://api.rawg.io/api/games", count)
                                fillListView("https://api.rawg.io/api/games", count)
                            }
                        }
                    }
                })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}

fun Fragment?.runOnUiThread(action: () -> Unit) {
    this ?: return
    if (!isAdded) return // Fragment not attached to an Activity
    activity?.runOnUiThread(action)
}


//val client = OkHttpClient()
//val request = Request.Builder()
//    .url("https://api.rawg.io/api/games?search=$s")
//    .get()
//    .addHeader("key", "1dce486d4f3f411c9d01d69e6849cf84")
//    .build()
//
//val response = client.newCall(request).enqueue(object : Callback {
//    override fun onFailure(request: Request?, e: IOException?) {
//        e?.printStackTrace()
//    }
//
//    override fun onResponse(response: Response?) {
//        var body = response?.body()?.string()
//        var gson = Gson()
//
//        val data = gson.fromJson(
//            body,
//            com.example.kotlin_video_games.json_game_list.Response::class.java
//        )
//
//        if (count >= 3) {
//            runOnUiThread {
//                fillViewPager("https://api.rawg.io/api/games?search=$s")
//                fillRecyclerView("https://api.rawg.io/api/games?search=$s")
//            }
//        }
//        else {
//            runOnUiThread {
//                fillViewPager("https://api.rawg.io/api/games")
//                fillRecyclerView("https://api.rawg.io/api/games")
//            }
//        }
//    }
//})

