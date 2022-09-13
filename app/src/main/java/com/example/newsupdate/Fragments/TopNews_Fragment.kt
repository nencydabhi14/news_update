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
import com.example.newsupdate.Adaptor.GoggleNewsAdaptor
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.*
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopNews_Fragment : Fragment() {

    lateinit var recycler_news: RecyclerView
    lateinit var shimmer1: ShimmerFrameLayout
    lateinit var shimmer2: ShimmerFrameLayout
    lateinit var shimmer3: ShimmerFrameLayout
    lateinit var shimmer4: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_top_news_, container, false)
        recycler_news = view.findViewById(R.id.recycler_news)
        shimmer1 = view.findViewById(R.id.shimmer1)
        shimmer2 = view.findViewById(R.id.shimmer2)
        shimmer3 = view.findViewById(R.id.shimmer3)
        shimmer4 = view.findViewById(R.id.shimmer4)

        return view
    }

    fun getNewsUpdate(source: String) {

        var apiNewsInterFace = ApiNewsClient().getRetrofit().create(ApiNewsInterFace::class.java)

        apiNewsInterFace.getgoogletopnews(source, "fd4950e741904254a43930571645080d")
            .enqueue(object : Callback<GoogleNewsApiModel> {
                override fun onResponse(
                    call: Call<GoogleNewsApiModel>,
                    response: Response<GoogleNewsApiModel>
                ) {
                    var list = response.body()?.articles!!
                    shimmer1.isVisible = false
                    shimmer2.isVisible = false
                    shimmer3.isVisible = false
                    shimmer4.isVisible = false
                    recycler_news.isVisible = true
                    setUpRvNews(list)
                }

                override fun onFailure(call: Call<GoogleNewsApiModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })
    }

    fun setUpRvNews(list: List<ArticlesItemGoogle?>) {
        var goggleNewsAdaptor = GoggleNewsAdaptor(activity, list)
        var lm = LinearLayoutManager(activity)
        recycler_news.adapter = goggleNewsAdaptor
        recycler_news.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()
        getNewsUpdate("google-news-in")
    }
}