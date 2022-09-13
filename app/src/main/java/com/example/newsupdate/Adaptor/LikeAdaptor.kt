package com.example.newsupdate.Adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsupdate.Screen.AllLikePostScreen
import com.example.newsupdate.ModelData
import com.example.newsupdate.R
import com.example.newsupdate.Screen.WebviewAllLike_Screen

class LikeAdaptor(val allLikePostScreen: AllLikePostScreen, val l1: ArrayList<ModelData>) :
    RecyclerView.Adapter<LikeAdaptor.ViewData>() {

    class ViewData(itemView: View) : NewsAdaptor.ViewHolder(itemView) {
        var author_txt = itemView.findViewById<TextView>(R.id.author_txt)
        var title_like_txt = itemView.findViewById<TextView>(R.id.title_like_txt)
        var description_like_txt = itemView.findViewById<TextView>(R.id.description_like_txt)
        var author_like = itemView.findViewById<TextView>(R.id.author_like)
        var published_At_like = itemView.findViewById<TextView>(R.id.published_At_like)
        var content_like = itemView.findViewById<TextView>(R.id.content_like)
        var read_more_like = itemView.findViewById<TextView>(R.id.read_more_like)
        var read_less_like = itemView.findViewById<TextView>(R.id.read_less_like)
        var full_contains = itemView.findViewById<CardView>(R.id.full_contains)
        var img_view_like = itemView.findViewById<ImageView>(R.id.img_view_like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(allLikePostScreen).inflate(R.layout.design_like, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.title_like_txt.text = l1[position].title
        holder.description_like_txt.text = l1[position].description
        holder.author_like.text = l1[position].author
        holder.published_At_like.text = l1[position].publishedAt
        holder.content_like.text = l1[position].content
        Glide.with(allLikePostScreen).load(l1[position].urlToImage).placeholder(R.drawable.newslogo)
            .into(holder.img_view_like)

        holder.read_more_like.setOnClickListener {
            holder.author_like.isVisible = true
            holder.published_At_like.isVisible = true
            holder.content_like.isVisible = true

            holder.read_more_like.isVisible = false
            holder.read_less_like.isVisible = true
        }

        holder.read_less_like.setOnClickListener {
            holder.author_like.isVisible = false
            holder.published_At_like.isVisible = false
            holder.content_like.isVisible = false

            holder.read_more_like.isVisible = true
            holder.read_less_like.isVisible = false
        }

        holder.full_contains.setOnClickListener {

            var intent = Intent(allLikePostScreen, WebviewAllLike_Screen::class.java)
            intent.putExtra("more",l1[position].url)
            allLikePostScreen.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return l1.size
    }
}