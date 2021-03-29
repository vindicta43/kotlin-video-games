package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.models.ApiModel
import com.google.gson.Gson
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectClient()
    }

    // test connection and gson test
    fun connectClient() {
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

                val data = gson.fromJson(body, com.example.kotlin_video_games.json_game_list.Response::class.java)
            }
        })
    }
}