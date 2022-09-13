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
import com.example.newsupdate.Retrofit.ApiNewsClient
import com.example.newsupdate.Retrofit.ApiNewsInterFace
import com.example.newsupdate.Retrofit.ArticlesItem
import com.example.newsupdate.Retrofit.NewsModelData
import com.example.newsupdate.databinding.FragmentBusinessBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Business_Fragment : Fragment() {

    lateinit var binding: FragmentBusinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBusinessBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    fun GetBusinessNews(country: String, category: String){
        var apiNewsInterFace = ApiNewsClient().getRetrofit().create(ApiNewsInterFace::class.java)

        apiNewsInterFace.getNews(country, category, "fd4950e741904254a43930571645080d")
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

                    binding.businessRecycler.isVisible = true

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
        binding.businessRecycler.adapter = newsAdaptor
        binding.businessRecycler.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()

        GetBusinessNews("us", "business")

    }
}