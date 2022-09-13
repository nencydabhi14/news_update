package com.example.newsupdate.Screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.newsupdate.databinding.ActivityNewsWebViewScreenBinding


class NewsWebView_Screen : AppCompatActivity() {

    lateinit var binding: ActivityNewsWebViewScreenBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewsWebViewScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val more = intent.getStringExtra("more")

        binding.webView.setWebViewClient(WebViewClient())
        binding.webView.getSettings().setJavaScriptEnabled(true)
        binding.webView.clearCache(true)

        binding.webView.loadUrl(more!!)

        binding.webView.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    binding.webLoadingD.isVisible = false
                }
            }
        })
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}