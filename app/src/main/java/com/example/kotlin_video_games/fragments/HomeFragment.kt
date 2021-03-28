package com.example.kotlin_video_games.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.viewpager.widget.ViewPager
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.models.GameAdapter
import com.example.kotlin_video_games.models.ModelGameItem
import com.example.kotlin_video_games.models.ViewPagerAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // implementation for ui elements
        var pager = view.findViewById<ViewPager>(R.id.viewPager)
        super.onViewCreated(view, savedInstanceState)
        var tempImg = listOf<Int>(R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher)
        var tempTxt = listOf<String>("PAGE ONE","PAGE TWO","PAGE THREE")
        var adapter = ViewPagerAdapter(tempImg, tempTxt, view.context)
        pager.adapter = adapter

        var recyclerView = view.findViewById<ListView>(R.id.recyclerView)
        var list = mutableListOf<ModelGameItem>()

        // for testing listView
        for (i in 0..50) {
            list.add(ModelGameItem("minecraft", "10", "2005", R.drawable.ic_favorite))
        }

        recyclerView.adapter =
            activity?.let { GameAdapter(it.applicationContext, R.layout.game_item, list) }
    }
}
