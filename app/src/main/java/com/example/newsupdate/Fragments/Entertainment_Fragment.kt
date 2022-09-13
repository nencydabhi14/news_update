package com.example.newsupdate.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.Adaptor.NewsAdaptor
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.ApiNewsClient
import com.example.newsupdate.Retrofit.ApiNewsInterFace
import com.example.newsupdate.Retrofit.ArticlesItem
import com.example.newsupdate.Retrofit.NewsModelData
import com.example.newsupdate.databinding.FragmentEntertainmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Entertainment_Fragment : Fragment() {

    lateinit var binding: FragmentEntertainmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEntertainmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    fun GetEntertainmentNews(country: String, category: String){
        var apiNewsInterFace = ApiNewsClient().getRetrofit().create(ApiNewsInterFace::class.java)

        apiNewsInterFace.getNews(country, category, "d3fbd29b9e584c7d81de1ec074c971b2")
            .enqueue(object : Callback<NewsModelData> {
                override fun onResponse(
                    call: Call<NewsModelData>,
                    response: Response<NewsModelData>
                ) {

                    var list = response.body()?.articles

                    binding.shimmer1.isVisible = false
                    binding.shimmer2.isVisible = false
                    binding.shimmer3.isVisible = false
                    binding.shimmer4.isVisible = false

                    binding.entertainmentRecycler.isVisible = true

                    setUpRvNews(list)
                }

                override fun onFailure(call: Call<NewsModelData>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })
    }
    fun setUpRvNews(list: List<ArticlesItem?>?) {
        var newsAdaptor = NewsAdaptor(activity, list)
        var lm = LinearLayoutManager(activity)
        binding.entertainmentRecycler.adapter = newsAdaptor
        binding.entertainmentRecycler.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()

        GetEntertainmentNews("in", "entertainment")

    }
}