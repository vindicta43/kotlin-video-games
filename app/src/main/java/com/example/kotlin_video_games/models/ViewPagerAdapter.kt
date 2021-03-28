package com.example.kotlin_video_games.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.kotlin_video_games.R

class ViewPagerAdapter(val images: List<Int>, val titles: List<String>,val ctx: Context) : PagerAdapter() {

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
        img.setImageResource(images.get(position))
        txt.setText(titles.get(position))
        container.addView(view, 0)
        return view
    }
}