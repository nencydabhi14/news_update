package com.example.newsupdate.Screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.newsupdate.Adaptor.Tabview_Adapter
import com.example.newsupdate.R
import com.example.newsupdate.Retrofit.ArticlesItem
import com.example.newsupdate.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var list = listOf<ArticlesItem?>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)

        binding.menuImg.setOnClickListener {
            var dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.dialog_bottom_menu)
            dialog.show()

            var like_page = dialog.findViewById<RelativeLayout>(R.id.like_page)
            var save_page = dialog.findViewById<RelativeLayout>(R.id.save_page)

            like_page!!.setOnClickListener {
                var intent = Intent(this, AllLikePostScreen::class.java)
                startActivity(intent)

                dialog.dismiss()
            }

            save_page!!.setOnClickListener {
                var intent = Intent(this, AllSavePostScreen::class.java)
                startActivity(intent)

                dialog.dismiss()
            }

        }



        binding.tabView.addTab(binding.tabView.newTab().setText("Top News"))
        binding.tabView.addTab(binding.tabView.newTab().setText("India"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Health"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Business"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Entertainment"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Science"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Sports"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Technology"))
        binding.tabView.addTab(binding.tabView.newTab().setText("Original"))

        var adpter1 =
            Tabview_Adapter(this@MainActivity, supportFragmentManager, binding.tabView.tabCount)
        binding.pagerView.adapter = adpter1

        binding.pagerView.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabView))
        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.pagerView.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.homeContain.setOnClickListener {

            binding.homeImg.setImageResource(R.drawable.home)
            binding.homeTxt.setTextColor(Color.parseColor("#FFFFFF"))
            binding.homeView.isVisible = true

            binding.videoImg.setImageResource(R.drawable.videod)
            binding.videoTxt.setTextColor(Color.parseColor("#A3A4A6"))
            binding.videoView.isVisible = false
            binding.profileImg.setImageResource(R.drawable.profiled)
            binding.profileTxt.setTextColor(Color.parseColor("#A3A4A6"))
            binding.profileView.isVisible = false

        }

        binding.videoContain.setOnClickListener {
            binding.videoImg.setImageResource(R.drawable.video)
            binding.videoTxt.setTextColor(Color.parseColor("#FFFFFF"))
            binding.videoView.isVisible = true

            binding.homeImg.setImageResource(R.drawable.homed)
            binding.homeTxt.setTextColor(Color.parseColor("#A3A4A6"))
            binding.homeView.isVisible = false

            binding.profileImg.setImageResource(R.drawable.profiled)
            binding.profileTxt.setTextColor(Color.parseColor("#A3A4A6"))
            binding.profileView.isVisible = false

            var intent = Intent(this, Video_Screen::class.java)
            startActivity(intent)
        }

        binding.profileContain.setOnClickListener {
            binding.profileImg.setImageResource(R.drawable.profile)
            binding.profileTxt.setTextColor(Color.parseColor("#FFFFFF"))
            binding.profileView.isVisible = true


            binding.videoImg.setImageResource(R.drawable.videod)
            binding.videoTxt.setTextColor(Color.parseColor("#A3A4A6"))
            binding.videoView.isVisible = false

            binding.homeImg.setImageResource(R.drawable.homed)
            binding.homeTxt.setTextColor(Color.parseColor("#A3A4A6"))
            binding.homeView.isVisible = false

            var intent = Intent(this, EditProfile_Screen::class.java)
            startActivity(intent)
        }
    }


    fun logoutAccountGoggle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        val googleSignInClient = GoogleSignIn.getClient(this,gso)
        googleSignInClient.signOut()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        var check = checkInternet()

        showDialog(check)

    }

    private fun internetDialog(): AlertDialog {
        var alertDialog = AlertDialog.Builder(this).setCancelable(false).setTitle("Internet off")
            .setMessage("Please on Internet")
            .setPositiveButton("Retry", object : DialogInterface.OnClickListener {
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    var check = checkInternet()
                    showDialog(check)
                }
            }).create()

        return alertDialog
    }

    private fun showDialog(check: Boolean) {
        var alertDialog: AlertDialog = internetDialog()
        if (check == false) {
            alertDialog.show()
        } else {
            alertDialog.dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ServiceCast")
    fun checkInternet(): Boolean {
        var connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.activeNetwork != null && connectivityManager.activeNetworkInfo!!.isConnected) {
            return true
        } else {
            return false
        }

    }


    override fun onResume() {
        super.onResume()

        binding.homeImg.setImageResource(R.drawable.home)
        binding.homeTxt.setTextColor(Color.parseColor("#FFFFFF"))
        binding.homeView.isVisible = true

        binding.videoImg.setImageResource(R.drawable.videod)
        binding.videoTxt.setTextColor(Color.parseColor("#A3A4A6"))
        binding.videoView.isVisible = false
        binding.profileImg.setImageResource(R.drawable.profiled)
        binding.profileTxt.setTextColor(Color.parseColor("#A3A4A6"))
        binding.profileView.isVisible = false

    }
}