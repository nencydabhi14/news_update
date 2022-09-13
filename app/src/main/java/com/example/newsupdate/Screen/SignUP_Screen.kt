package com.example.newsupdate.Screen

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivitySignUpScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUP_Screen : AppCompatActivity() {

    lateinit var binding: ActivitySignUpScreenBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.offblack)

        binding.passwordEdtSignup.setOnClickListener {
            binding.cardCreate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        }


        binding.createAccountTxt.setOnClickListener {
            createAccount(
                binding.emailEdtSignup.text.toString(),
                binding.passwordEdtSignup.text.toString()
            )
            binding.Progressbar.isVisible = true
            binding.createAccountTxt.isVisible = false
        }
    }

    fun createAccount(email: String, password: String) {
        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener { res ->
            Toast.makeText(this, "Your Account create Successfully", Toast.LENGTH_LONG).show()
            binding.Progressbar.isVisible = false
            binding.createAccountTxt.isVisible = true


            finish()

        }.addOnFailureListener { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show()
            binding.createAccountTxt.isVisible = true
            binding.Progressbar.isVisible = false
        }
    }
}