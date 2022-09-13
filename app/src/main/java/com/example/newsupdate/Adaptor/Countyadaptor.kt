package com.example.newsupdate.Adaptor

import android.annotation.SuppressLint
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.newsfire10.Utils
import com.example.newsupdate.R
import com.example.newsupdate.Screen.Select_Country_Screen
import com.example.newsupdate.Screen.Static_Class.Companion.county
import com.example.newsupdate.Screen.Static_Class.Companion.county_names

import java.io.InputStream


class Countyadaptor(
    val selectCountryScreen: Select_Country_Screen,
    val c_name: Array<String>,
    val county_name: Array<String>,
    val img_county: Array<String>
) : RecyclerView.Adapter<Countyadaptor.ViewData>() {
    var list = arrayListOf<Int>(
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack,
        R.color.offblack
    )

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cname_txt = itemView.findViewById<TextView>(com.example.newsupdate.R.id.cname_txt)
        var county_image = itemView.findViewById<ImageView>(R.id.county_image)
        var click_card = itemView.findViewById<CardView>(R.id.click_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(selectCountryScreen)
            .inflate(R.layout.county_select_item, parent, false)
        return ViewData(view)
    }

    @SuppressLint("NewApi", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.cname_txt.text = c_name[position]


        Utils().fetchSVG(selectCountryScreen,img_county[position],holder.county_image)


        holder.click_card.setCardBackgroundColor(selectCountryScreen.getColor(list[position]))

        var i = 0
        holder.click_card.setOnClickListener {

            county_names = c_name[position]
            county = county_name[position]

            list[position] = R.color.grey
            holder.click_card.setCardBackgroundColor(selectCountryScreen.getColor(list[position]))
            while (i < list.size) {
                if (i != position) {
                    list[i] = R.color.offblack
                }
                i++
            }
            notifyDataSetChanged()
        }

        Log.e("TAG", "onBindViewHolderCounty2: $county")
    }


    override fun getItemCount(): Int {
        return img_county.size
    }
}