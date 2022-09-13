package com.example.newsupdate.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.Adaptor.NewsAdaptor
import com.example.newsupdate.Retrofit.*
import com.example.newsupdate.Screen.Select_Country_Screen
import com.example.newsupdate.Screen.Static_Class.Companion.county
import com.example.newsupdate.Screen.Static_Class.Companion.county_names
import com.example.newsupdate.databinding.FragmentSelectCityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Select_City_Fragment : Fragment() {

    lateinit var binding: FragmentSelectCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectCityBinding.inflate(layoutInflater, container, false)

        binding.selectCity.setOnClickListener {
            var intent = Intent(activity, Select_Country_Screen::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun getnewscounty(country: String, category: String) {
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

                    binding.recyclerCityNews.isVisible = true
                    binding.selectCityTxt.isVisible = true

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
        binding.recyclerCityNews.adapter = newsAdaptor
        binding.recyclerCityNews.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()
        getnewscounty(county, "business")

        binding.countySelectName.text = county_names
    }
}