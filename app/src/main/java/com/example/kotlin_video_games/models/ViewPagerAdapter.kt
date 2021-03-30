package com.example.kotlin_video_games.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.example.kotlin_video_games.MainActivity
import com.example.kotlin_video_games.R
import com.example.kotlin_video_games.fragments.DetailFragment
import com.squareup.picasso.Picasso

class ViewPagerAdapter(val images: List<String?>, val titles: List<String?>, val ctx: Context?) :
    PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        var view = layoutInflater.inflate(R.layout.viewpager_item, container, false)
        val img = view.findViewById<ImageView>(R.id.vpGame)
        val txt = view.findViewById<TextView>(R.id.vpTitle)
        Picasso.get().load(images[position]).into(img)
        txt.setText(titles[position])
        container.addView(view, 0)
        return view
    }
}