package com.example.kotlin_video_games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlin_video_games.fragments.DetailFragment
import com.example.kotlin_video_games.fragments.FavoriteListFragment
import com.example.kotlin_video_games.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favoriteList = FavoriteListFragment()
        val favorite = DetailFragment()
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)

        makeFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
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