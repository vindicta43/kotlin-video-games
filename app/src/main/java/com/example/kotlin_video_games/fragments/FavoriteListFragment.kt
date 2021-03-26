package com.example.kotlin_video_games.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.models.ModelGameItem
import com.example.kotlin_video_games.models.MyAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
            activity?.let { MyAdapter(it.applicationContext, R.layout.game_item, list) }

        //listView.setOnItemClickListener()
    }
}