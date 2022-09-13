package com.example.newsupdate.Screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivitySkippedPageBinding
import com.google.firebase.auth.FirebaseAuth
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class Skipped_Page : AppCompatActivity() {


    lateinit var binding: ActivitySkippedPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySkippedPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.offblack)

        binding.carousel.registerLifecycle(lifecycle)
        val list = mutableListOf<CarouselItem>()

        binding.skipTxt.setOnClickListener {
            var intent = Intent(this@Skipped_Page,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        list.add(
            CarouselItem(
                imageUrl = "https://ksenvironmental.com.au/wp-content/uploads/2015/02/iStock_000008189719Medium.jpg"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://trainingindustry.com/content/uploads/2018/10/MA-in-the-Training-Industry-10.11.18.jpg"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://media.ma-no.org/imgr/1280-640/admin-newswire.jpg"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://eforensicsmag.com/wp-content/uploads/2021/06/newsef.png"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://yesofcorsa.com/wp-content/uploads/2017/06/4K-Newspapers-Photo-Free.jpg"
            )
        )
        binding.carousel.setData(list)


    }
}