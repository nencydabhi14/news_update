package com.example.newsupdate.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.Adaptor.GoggleNewsAdaptor
import com.example.newsupdate.Adaptor.NewsAdaptor
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.*
import com.example.newsupdate.databinding.FragmentOriginalNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Original_news_Fragment : Fragment() {

    lateinit var binding: FragmentOriginalNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOriginalNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
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

                    binding.shimmer1.isVisible = false
                    binding.shimmer2.isVisible = false
                    binding.shimmer3.isVisible = false
                    binding.shimmer4.isVisible = false

                    binding.originalRecycler.isVisible = true

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
        binding.originalRecycler.adapter = goggleNewsAdaptor
        binding.originalRecycler.layoutManager = lm
    }

    override fun onStart() {
        super.onStart()
        getNewsUpdate("techcrunch")
    }
}