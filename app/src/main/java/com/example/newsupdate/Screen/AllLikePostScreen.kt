package com.example.newsupdate.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.Adaptor.LikeAdaptor
import com.example.newsupdate.Db_helper
import com.example.newsupdate.ModelData
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivityAllLikePostScreenBinding

class AllLikePostScreen : AppCompatActivity() {

    lateinit var binding: ActivityAllLikePostScreenBinding
    var list = arrayListOf<ModelData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAllLikePostScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)


        binding.backLike.setOnClickListener {
            onBackPressed()
        }

        var db = Db_helper(this)
        list = db.ReadData()

        setUpRV(list)
    }

    fun setUpRV(l1: ArrayList<ModelData>) {
        var adaptor = LikeAdaptor(this, l1)
        var lm = LinearLayoutManager(this)
        binding.likeRecycler.layoutManager = lm
        binding.likeRecycler.adapter = adaptor
    }

    override fun onResume() {
        if (list.size.equals(0)) {
            binding.animlike.isVisible = true
            binding.nolike.isVisible = true
        } else {
            binding.animlike.isVisible = false
            binding.nolike.isVisible = false
        }
        super.onResume()
    }
}