package com.example.newsupdate.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.Adaptor.ArticlesItem
import com.example.newsupdate.Adaptor.HealthModelApiData
import com.example.newsupdate.Adaptor.HelthNewsAdaptor
import com.example.newsupdate.Retrofit.ApiNewsClient
import com.example.newsupdate.Retrofit.ApiNewsInterFace
import com.example.newsupdate.databinding.FragmentLatestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Latest_Fragment : Fragment() {

    companion object {
        lateinit var binding: FragmentLatestBinding
        var list = listOf<ArticlesItem>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLatestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun getHealth() {
        var apiNewsInterFace = ApiNewsClient().retrofitstatic().create(ApiNewsInterFace::class.java)

        apiNewsInterFace.gethelthdata().enqueue(object : Callback<HealthModelApiData> {
            override fun onResponse(
                call: Call<HealthModelApiData>,
                response: Response<HealthModelApiData>
            ) {
                var list1 = response.body()?.articles!!
                Log.e("TAG", "onResponse==================: $list1")

                binding.shimmer1.isVisible = false
                binding.shimmer2.isVisible = false
                binding.shimmer3.isVisible = false
                binding.shimmer4.isVisible = false

                binding.recyclerHealthNews.isVisible = true

                setUpRvNews(list1)
            }

            override fun onFailure(call: Call<HealthModelApiData>, t: Throwable) {

                Log.e("TAG", "onFailure===================: ${t.message}")

            }
        })
    }

    fun setUpRvNews(list1: List<com.example.newsupdate.Adaptor.ArticlesItem?>) {
        var helthNewsAdaptor = HelthNewsAdaptor(activity, list1)
        var lm = LinearLayoutManager(activity)
        binding.recyclerHealthNews.adapter = helthNewsAdaptor
        binding.recyclerHealthNews.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()
        getHealth()
    }

}