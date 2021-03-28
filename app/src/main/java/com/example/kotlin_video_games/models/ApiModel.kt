package com.example.kotlin_video_games.models

import com.squareup.okhttp.*
import java.io.IOException

open class ApiModel {
    companion object {
        //var client = OkHttpClient()
        //
        //var request = Request.Builder()
        //    .url("https://rawg-video-games-database.p.rapidapi.com/games")
        //    .get()
        //    .addHeader("x-rapidapi-key", "20b281e554msh8b74305ebe8a24ap162013jsn79f1204a2ed4")
        //    .addHeader("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
        //    .build()
        //
        //var response = client.newCall(request).execute()

        val okClient by lazy {
            OkHttpClient()
        }
        val okRequest by lazy {
            Request.Builder()
                .url("https://rawg-video-games-database.p.rapidapi.com/games")
                .get()
                .addHeader("x-rapidapi-key", "20b281e554msh8b74305ebe8a24ap162013jsn79f1204a2ed4")
                .addHeader("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
                .build()
        }
    }
}