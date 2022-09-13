package com.example.newsupdate.Screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.newsupdate.databinding.ActivitySplashScreenPageBinding
import com.google.firebase.auth.FirebaseAuth

class Splash_Screen_page : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser

        Handler(Looper.getMainLooper()).postDelayed({
            if (user == null) {
                var intent = Intent(this, Profile_Screen::class.java)
                startActivity(intent)

            } else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 5500)
    }
}