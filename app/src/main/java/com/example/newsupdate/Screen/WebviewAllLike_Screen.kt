package com.example.newsupdate.Screen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.example.newsupdate.databinding.ActivityWebviewAllLikeScreenBinding

class WebviewAllLike_Screen : AppCompatActivity() {

    lateinit var binding: ActivityWebviewAllLikeScreenBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWebviewAllLikeScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val more = intent.getStringExtra("more")

        binding.webViewLike.setWebViewClient(WebViewClient())
        binding.webViewLike.getSettings().setJavaScriptEnabled(true)
        binding.webViewLike.clearCache(true)

        binding.webViewLike.loadUrl(more!!)

        binding.webViewLike.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    binding.webLoading.isVisible = false
                }
            }
        })
    }

    override fun onBackPressed() {
        if (binding.webViewLike.canGoBack()) {
            binding.webViewLike.goBack()
        } else {
            super.onBackPressed()
        }
    }

}