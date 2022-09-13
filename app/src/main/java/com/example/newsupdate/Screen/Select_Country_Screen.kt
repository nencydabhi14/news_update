package com.example.newsupdate.Screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsupdate.Adaptor.Countyadaptor
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivitySelectCountryScreenBinding

class Select_Country_Screen : AppCompatActivity() {

    lateinit var binding: ActivitySelectCountryScreenBinding
    var c_name = arrayOf(
        "Argentina", "Australia", "Austria",
        "Belgium",
        "Brazil",
        "Bulgaria",
        "Canada",
        "China",
        "Colombia",
        "Cuba",
        "Czech Republic",
        "Egypt",
        "France",
        "Germany",
        "Greece",
        "Hong Kong",
        "Hungary",
        "India",
        "Indonesia",
        "Ireland",
        "Israel",
        "Italy",
        "Japan",
        "Latvia",
        "Lithuania",
        "Malaysia",
        "Mexico",
        "Morocco",
        "Netherlands",
        "New Zealand",
        "Nigeria",
        "Norway",
        "Philippines",
        "Poland",
        "Portugal",
        "Romania",
        "Russia",
        "Saudi Arabia",
        "Serbia",
        "Singapore",
        "Slovakia",
        "Slovenia",
        "South Africa",
        "South Korea",
        "Sweden",
        "Switzerland",
        "taiwan",
        "Thailand",
        "Turkey",
        "UAE",
        "Ukraine",
        "United States",
        "Venezuela"
    )
    var county_name = arrayOf(
        "ar",
        "au",
        "at",
        "be",
        "br",
        "bg",
        "ca",
        "cn",
        "co",
        "cu",
        "cz",
        "eg",
        "fr",
        "de",
        "gr",
        "hk",
        "hu",
        "in",
        "id",
        "ie",
        "il",
        "it",
        "jp",
        "lv",
        "lt",
        "my",
        "mx",
        "ma",
        "nl",
        "nz",
        "ng",
        "no",
        "ph",
        "pl",
        "pt",
        "ro",
        "ru",
        "sa",
        "rs",
        "sg",
        "sk",
        "si",
        "za",
        "kr",
        "se",
        "ch",
        "tw",
        "th",
        "tr",
        "ae",
        "ua",
        "us",
        "ve"
    )
    var img_county = arrayOf("https://newsapi.org/images/flags/ar.svg","https://newsapi.org/images/flags/au.svg",
    "https://newsapi.org/images/flags/at.svg","https://newsapi.org/images/flags/be.svg","https://newsapi.org/images/flags/br.svg",
    "https://newsapi.org/images/flags/bg.svg","https://newsapi.org/images/flags/ca.svg","https://newsapi.org/images/flags/cn.svg",
    "https://newsapi.org/images/flags/co.svg","https://newsapi.org/images/flags/cu.svg","https://newsapi.org/images/flags/cz.svg",
    "https://newsapi.org/images/flags/eg.svg","https://newsapi.org/images/flags/fr.svg","https://newsapi.org/images/flags/de.svg",
    "https://newsapi.org/images/flags/gr.svg","https://newsapi.org/images/flags/hk.svg","https://newsapi.org/images/flags/hu.svg",
    "https://newsapi.org/images/flags/in.svg","https://newsapi.org/images/flags/id.svg","https://newsapi.org/images/flags/ie.svg",
    "https://newsapi.org/images/flags/il.svg","https://newsapi.org/images/flags/it.svg","https://newsapi.org/images/flags/jp.svg",
    "https://newsapi.org/images/flags/lv.svg","https://newsapi.org/images/flags/lt.svg","https://newsapi.org/images/flags/my.svg",
    "https://newsapi.org/images/flags/mx.svg","https://newsapi.org/images/flags/ma.svg","https://newsapi.org/images/flags/nl.svg",
    "https://newsapi.org/images/flags/nz.svg","https://newsapi.org/images/flags/ng.svg","https://newsapi.org/images/flags/no.svg",
    "https://newsapi.org/images/flags/ph.svg","https://newsapi.org/images/flags/pl.svg","https://newsapi.org/images/flags/pt.svg",
    "https://newsapi.org/images/flags/ro.svg","https://newsapi.org/images/flags/ru.svg","https://newsapi.org/images/flags/sa.svg",
    "https://newsapi.org/images/flags/rs.svg","https://newsapi.org/images/flags/sg.svg","https://newsapi.org/images/flags/sk.svg",
    "https://newsapi.org/images/flags/si.svg","https://newsapi.org/images/flags/za.svg","https://newsapi.org/images/flags/kr.svg",
    "https://newsapi.org/images/flags/se.svg","https://newsapi.org/images/flags/ch.svg","https://newsapi.org/images/flags/tw.svg",
    "https://newsapi.org/images/flags/th.svg","https://newsapi.org/images/flags/tr.svg","https://newsapi.org/images/flags/ae.svg",
    "https://newsapi.org/images/flags/ua.svg","https://newsapi.org/images/flags/us.svg","https://newsapi.org/images/flags/ve.svg")

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelectCountryScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
        binding.backImg.setOnClickListener {
            onBackPressed()
        }

        binding.okBtn.setOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        var countyadaptor = Countyadaptor(this, c_name, county_name,img_county)
        var lm = GridLayoutManager(this, 2)
        binding.countySelectRecycler.adapter = countyadaptor
        binding.countySelectRecycler.layoutManager = lm
        binding.countySelectRecycler.post(Runnable { countyadaptor.notifyDataSetChanged() })

    }
}