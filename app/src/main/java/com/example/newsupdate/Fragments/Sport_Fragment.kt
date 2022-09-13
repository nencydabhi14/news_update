package com.example.newsupdate.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsupdate.Adaptor.NewsAdaptor
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.ApiNewsClient
import com.example.newsupdate.Retrofit.ApiNewsInterFace
import com.example.newsupdate.Retrofit.ArticlesItem
import com.example.newsupdate.Retrofit.NewsModelData
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Sport_Fragment : Fragment() {

    lateinit var sport_recycler: RecyclerView
    lateinit var shimmer1: ShimmerFrameLayout
    lateinit var shimmer2: ShimmerFrameLayout
    lateinit var shimmer3: ShimmerFrameLayout
    lateinit var shimmer4: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_sport_, container, false)
        sport_recycler = view.findViewById(R.id.sport_recycler)
        shimmer1 = view.findViewById(R.id.shimmer1)
        shimmer2 = view.findViewById(R.id.shimmer2)
        shimmer3 = view.findViewById(R.id.shimmer3)
        shimmer4= view.findViewById(R.id.shimmer4)
        return view
    }

    fun GetScienceNews(country: String, category: String){
        var apiNewsInterFace = ApiNewsClient().getRetrofit().create(ApiNewsInterFace::class.java)

        apiNewsInterFace.getNews(country, category, "fd4950e741904254a43930571645080d")
            .enqueue(object : Callback<NewsModelData> {
                override fun onResponse(
                    call: Call<NewsModelData>,
                    response: Response<NewsModelData>
                ) {

                    var list = response.body()?.articles

                    shimmer1.isVisible = false
                    shimmer2.isVisible = false
                    shimmer3.isVisible = false
                    shimmer4.isVisible = false

                    sport_recycler.isVisible = true

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
        sport_recycler.adapter = newsAdaptor
        sport_recycler.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()

        GetScienceNews("in", "sport")

    }
}