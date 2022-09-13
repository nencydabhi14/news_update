package com.example.newsupdate.Adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsupdate.Fragments.*
import com.example.newsupdate.Screen.MainActivity

class Tabview_Adapter(
    val mainActivity: MainActivity,
    val supportFragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentPagerAdapter(
    supportFragmentManager
) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> TopNews_Fragment()
            1 -> Select_City_Fragment()
            2 -> Latest_Fragment()
            3 -> Business_Fragment()
            4 -> Entertainment_Fragment()
            5 -> Science_Fragment()
            6 -> Sport_Fragment()
            7 -> TechnologyFragment()
            8 -> Original_news_Fragment()
            else -> Latest_Fragment()
        }
    }
}