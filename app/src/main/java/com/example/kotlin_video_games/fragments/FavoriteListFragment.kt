package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.models.ModelGameItem
import com.example.kotlin_video_games.models.GameAdapter

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

        // listView test code
        // then I'll add API connection
        var listView = view.findViewById<ListView>(R.id.listView)
        var list = mutableListOf<ModelGameItem>()

        // for testing listView
        for (i in 0..50) {
            list.add(ModelGameItem("minecraft", "10", "2005", R.drawable.ic_favorite))
        }

        listView.adapter =
            activity?.let { GameAdapter(it.applicationContext, R.layout.game_item, list) }

        //listView.setOnItemClickListener()
    }
}