package com.example.newsupdate.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.*
import com.example.newsupdate.Adaptor.SaveAdaptor
import com.example.newsupdate.databinding.ActivityAllSavePostScreenBinding

class AllSavePostScreen : AppCompatActivity() {

    lateinit var binding: ActivityAllSavePostScreenBinding
    var list = arrayListOf<ModelSave>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAllSavePostScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)

        binding.backSave.setOnClickListener {
            onBackPressed()
        }

        var db = Db_save(this)
        list = db.ReadData()

        setUpRV(list)
    }
    fun setUpRV(l1: ArrayList<ModelSave>) {
        var adaptor = SaveAdaptor(this, l1)
        var lm = LinearLayoutManager(this)
        binding.saveRecycler.layoutManager = lm
        binding.saveRecycler.adapter = adaptor
    }

    override fun onResume() {
        if (list.size.equals(0)) {
            binding.animsave.isVisible = true
            binding.nosave.isVisible = true
        } else {
            binding.animsave.isVisible = false
            binding.nosave.isVisible = false
        }
        super.onResume()
    }
}