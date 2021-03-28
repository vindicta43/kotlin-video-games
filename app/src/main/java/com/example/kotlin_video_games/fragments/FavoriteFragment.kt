package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.models.ApiModel
import com.squareup.okhttp.Callback
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

        //ApiModel.client.newCall(ApiModel.request).enqueue(object : Callback {
        //    override fun onFailure(request: Request?, e: IOException?) {
        //        e?.printStackTrace()
        //    }
        //    override fun onResponse(response: Response?) {
        //        if (response?.isSuccessful == true) {
        //            var myResponse: String = response.body().string()
        //            //var txt: TextView = view.findViewById(R.id.gameName)
        //            //txt.setText(myResponse)
        //        }
        //    }
        //})

        // basic api connection for rapidapi
        ApiModel.okClient.newCall(ApiModel.okRequest).enqueue(object: Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(response: Response?) {
                val inputStream = response?.body()?.string()
                var tvGameName = view.findViewById<TextView>(R.id.gameDesc)
                runOnUiThread {
                    tvGameName.setText(inputStream)
                }
            }
        })
    }
}

// extension function for fragment
fun Fragment?.runOnUiThread(action: () -> Unit) {
    this ?: return
    if (!isAdded) return // Fragment not attached to an Activity
    activity?.runOnUiThread(action)
}