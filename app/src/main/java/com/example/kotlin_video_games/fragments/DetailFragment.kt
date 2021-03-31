package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.ResponseGameDetails
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

                    // ui elements
                    val bgImage = view.findViewById<ImageView>(R.id.ivDetail)
                    val gameName = view.findViewById<TextView>(R.id.tvGameName)
                    val gameReleased = view.findViewById<TextView>(R.id.tvReleaseDate)
                    val gameMetacritic = view.findViewById<TextView>(R.id.tvMetacriticRate)
                    val gameDesc = view.findViewById<TextView>(R.id.tvGameDesc)

                    val data = gson.fromJson(body, ResponseGameDetails::class.java)

                    runOnUiThread {
                        Picasso.get().load(data.backgroundImage).into(bgImage)
                        gameName.text = data.name
                        gameReleased.text = data.released
                        gameMetacritic.text = data.metacritic.toString()
                        gameDesc.text = data.description
                    }
                }
            }
        )

    }
}