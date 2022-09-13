package com.example.newsupdate.Adaptor

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsupdate.Db_helper
import com.example.newsupdate.Db_save
import com.example.newsupdate.Screen.Details_Screen
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.ArticlesItemGoogle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAdView

class GoggleNewsAdaptor(val activity: FragmentActivity?, val list: List<ArticlesItemGoogle?>) :
    RecyclerView.Adapter<GoggleNewsAdaptor.ViewData>() {

    open class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var news_description = itemView.findViewById<TextView>(R.id.news_description)
        var news_title = itemView.findViewById<TextView>(R.id.news_title)
        var news_img = itemView.findViewById<ImageView>(R.id.news_img)
        var like_contain = itemView.findViewById<RelativeLayout>(R.id.like_contain)
        var share_Contain = itemView.findViewById<RelativeLayout>(R.id.share_Contain)
        var save_contain = itemView.findViewById<RelativeLayout>(R.id.save_contain)
        var save_img = itemView.findViewById<ImageView>(R.id.save_img)

        var like_img = itemView.findViewById<ImageView>(R.id.like_img)
        var itm_file_contain = itemView.findViewById<RelativeLayout>(R.id.itm_file_contain)
        var like_txt = itemView.findViewById<TextView>(R.id.like_txt)
        var save_dtxt = itemView.findViewById<TextView>(R.id.save_dtxt)
        var ViewRelative = itemView.findViewById<RelativeLayout>(R.id.ViewRelative)
        var view1 = itemView.findViewById<View>(R.id.view1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(activity).inflate(R.layout.design_news, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.news_title.text = list.get(position)?.title
        holder.news_description.text = list.get(position)?.description

        Glide.with(activity!!).load(list.get(position)?.urlToImage)
            .placeholder(R.drawable.newslogo).into(holder.news_img)

        if (position % 4 == 0){

            holder.ViewRelative.isVisible = true
            holder.view1.isVisible = true

            val builder = AdLoader.Builder(activity!!, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd { nativeAD ->
                    var view = LayoutInflater.from(activity!!)
                        .inflate(R.layout.nativeadd_item, null) as NativeAdView

                    var bg_img = view.findViewById<ImageView>(R.id.bg_img)
                    var img_logo = view.findViewById<ImageView>(R.id.img_logo)
                    var title_txt = view.findViewById<TextView>(R.id.title_txt)
                    var subtitle_txt = view.findViewById<TextView>(R.id.subtitle_txt)

                    bg_img.setImageDrawable(nativeAD.images[0].drawable)
                    img_logo.setImageDrawable(nativeAD.icon?.drawable)
                    title_txt.setText(nativeAD.headline)
                    subtitle_txt.setText(nativeAD.body)

                    holder.ViewRelative.removeAllViews()
                    holder.ViewRelative.addView(view)

                }.withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        super.onAdFailedToLoad(p0)
                        Log.e("TAG,", "onAdFailedToLoad: $p0")
                    }
                }).build()

            builder.loadAd(AdRequest.Builder().build())

//                holder.yt_txr.isVisible = true
//            holder.yt_txr.text = "Done"
        }

        var clicked = false
        holder.like_contain.setOnClickListener {

            var db = Db_helper(activity)
            db.insertData(
                list[position]!!.author.toString(),
                list[position]!!.title.toString(),
                list[position]!!.description.toString(),
                list[position]!!.url.toString(),
                list[position]!!.urlToImage.toString(),
                list[position]!!.publishedAt.toString(),
                list[position]!!.content.toString())

            if (clicked) {
                clicked = false;
                holder.like_txt.text = "Like"
                holder.like_img.setImageResource(R.drawable.like);
            } else {
                clicked = true;
                holder.like_img.setImageResource(R.drawable.liked);
                holder.like_txt.text = "Liked"

                Toast.makeText(activity, "Liked  !!", Toast.LENGTH_SHORT).show()
            }
        }
        holder.share_Contain.setOnClickListener {

            var share = list[position]?.title

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody =
                "Hello USER,\nPlease Rate Quotes App On Play Store\n⭐️⭐️⭐️⭐️⭐️" +
                        "\n\nYOUR QUOTE\n \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\n\n $share"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, share)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            activity.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }


        var click_Save = false
        holder.save_contain.setOnClickListener {

            var db = Db_save(activity)
            db.insertData(
                list[position]!!.author.toString(),
                list[position]!!.title.toString(),
                list[position]!!.description.toString(),
                list[position]!!.url.toString(),
                list[position]!!.urlToImage.toString(),
                list[position]!!.publishedAt.toString(),
                list[position]!!.content.toString())

            if (click_Save) {
                click_Save = false
                holder.save_dtxt.text = "Save"
                holder.save_img.setImageResource(R.drawable.save);
            } else {
                click_Save = true
                holder.save_img.setImageResource(R.drawable.saved)
                holder.save_dtxt.text = "Saved"

                Toast.makeText(activity, "Save Successfully  !!", Toast.LENGTH_SHORT).show()
            }
        }


        holder.itm_file_contain.setOnClickListener {
            var intent = Intent(activity, Details_Screen::class.java)
            intent.putExtra("ps", position)
            intent.putExtra("author", list[position]?.author)
            intent.putExtra("description", list[position]?.description)
            intent.putExtra("title", list[position]?.title)
            intent.putExtra("url", list[position]?.url)
            intent.putExtra("urlToImage", list[position]?.urlToImage)
            intent.putExtra("publishedAt", list[position]?.publishedAt)
            intent.putExtra("content", list[position]?.content)
            activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun nativeADDCutome() {


    }

}