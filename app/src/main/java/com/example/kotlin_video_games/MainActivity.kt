package com.example.kotlin_video_games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.kotlin_video_games.fragments.FavoriteFragment
import com.example.kotlin_video_games.fragments.FavoriteListFragment
import com.example.kotlin_video_games.fragments.HomeFragment
import com.example.kotlin_video_games.models.ModelGameItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favoriteList = FavoriteListFragment()
        val favorite = FavoriteFragment()
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)


        // listView test code
        // then I'll add API connection
        var listView = findViewById<ListView>(R.id.listView)
        var list = mutableListOf<ModelGameItem>()
        list.add(ModelGameItem("minecraft", "10", "2005", R.drawable.ic_favorite))
        list.add(ModelGameItem("lorem", "5", "2002", R.drawable.ic_home))
        list.add(ModelGameItem("cod", "10", "2012", R.drawable.ic_favorite))


        makeFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> makeFragment(homeFragment)
                R.id.ic_favorite -> makeFragment(favoriteList)
            }
            true
        }
    }

    private fun makeFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
}