package com.example.newsupdate.Screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.ArticlesItem
import com.example.newsupdate.databinding.ActivityDetailsScreenBinding

class Details_Screen : AppCompatActivity() {

    lateinit var binding: ActivityDetailsScreenBinding
    var list = listOf<ArticlesItem?>()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)

        binding.backImgDetails.setOnClickListener {
            onBackPressed()
        }

        var ps = intent.getIntExtra("ps", 0)
        var author = intent.getStringExtra("author")
        var url = intent.getStringExtra("url")
        var urlToImage = intent.getStringExtra("urlToImage")
        var publishedAt = intent.getStringExtra("publishedAt")
        var content = intent.getStringExtra("content")
        var description = intent.getStringExtra("description")
        var title = intent.getStringExtra("title")

        Glide.with(this).load(urlToImage).placeholder(R.drawable.newslogo)
            .into(binding.imgDetails)
        binding.contentDetails.text = content
        binding.publishAtDetails.text = publishedAt
        binding.descriptionDetails.text = description
        binding.titleDetails.text = title
        binding.authorDetails.text = author

        binding.urlImgDetails.setOnClickListener {
            var intent = Intent(this,NewsWebView_Screen::class.java)
            intent.putExtra("more",url)
            startActivity(intent)
        }


        binding.likeDetail.setOnClickListener {
            var intent = Intent(this,AllLikePostScreen::class.java)
            startActivity(intent)
        }

        binding.saveDetail.setOnClickListener {
            var intent = Intent(this,AllSavePostScreen::class.java)
            startActivity(intent)
        }

        binding.shareDetail.setOnClickListener {
            var share = url

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody =
                "Hello USER,\nPlease Rate Quotes App On Play Store\n⭐️⭐️⭐️⭐️⭐️" +
                        "\n\nYOUR QUOTE\n \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\n\n $share"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,share)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }
}