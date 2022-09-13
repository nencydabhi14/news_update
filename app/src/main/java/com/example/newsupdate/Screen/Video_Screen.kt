package com.example.newsupdate.Screen

import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.newsupdate.Adaptor.VideoAdaptor
import com.example.newsupdate.ModelClass
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivityVideoScreenBinding

class Video_Screen : AppCompatActivity() {

    lateinit var binding: ActivityVideoScreenBinding

    var simpleVideoView: VideoView? = null
    var mediaControls: MediaController? = null

    var videoItems = ArrayList<ModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVideoScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Toast.makeText(this, "Hold Screen To Pause Video", Toast.LENGTH_LONG).show()

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        videoItems.add(ModelClass("https://assets.mixkit.co/videos/preview/mixkit-the-spheres-of-a-christmas-tree-2720-large.mp4"))
        videoItems.add(ModelClass("https://assets.mixkit.co/videos/preview/mixkit-giving-a-conference-to-reporters-24357-large.mp4"))
        videoItems.add(ModelClass("https://assets.mixkit.co/videos/preview/mixkit-gray-and-white-rat-32060-large.mp4"))
        videoItems.add(ModelClass("https://assets.mixkit.co/videos/preview/mixkit-politician-escaping-from-journalists-23111-large.mp4"))

        var videoAdapter = VideoAdaptor(this,videoItems)
        binding.viewPagerVideo.adapter = videoAdapter

//        simpleVideoView = findViewById<View>(R.id.simpleVideoView) as VideoView
//        if (mediaControls == null) {
//            // creating an object of media controller class
//            mediaControls = MediaController(this)
//
//            // set the anchor view for the video view
//            mediaControls!!.setAnchorView(this.simpleVideoView)
//        }
//        simpleVideoView!!.setMediaController(mediaControls)
//
//        simpleVideoView!!.setVideoURI(
//            Uri.parse(
//                "android.resource://"
//                        + packageName + "/" + R.raw.videocorona
//            )
//        )
//
//        simpleVideoView!!.requestFocus()
//        // starting the video
//        simpleVideoView!!.start()

        // display a toast message
        // after the video is completed
//        simpleVideoView!!.setOnCompletionListener {
//            Toast.makeText(
//                applicationContext, "Video completed",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
//            Toast.makeText(
//                applicationContext, "An Error Occurred " +
//                        "While Playing Video !!!", Toast.LENGTH_LONG
//            ).show()
//            false
//        }
//
//        var clicked = false
//        binding.likeVideo.setOnClickListener {
//            if (clicked) {
//                clicked = false;
//                binding.likeVideo.setImageResource(R.drawable.like);
//            } else {
//                clicked = true;
//                binding.likeVideo.setImageResource(R.drawable.liked);
//
//                Toast.makeText(this, "Liked  !!", Toast.LENGTH_SHORT).show()
//            }
//        }

//        binding.shareVideo.setOnClickListener {
//            var share = R.raw.videocorona
//
//            val sharingIntent = Intent(Intent.ACTION_SEND)
//            sharingIntent.type = "text/plain"
//            val shareBody =
//                "Hello USER,\nPlease Rate Quotes App On Play Store\n⭐️⭐️⭐️⭐️⭐️" +
//                        "\n\nYOUR QUOTE\n \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\n\n $share"
//            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,share)
//            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
//            this.startActivity(Intent.createChooser(sharingIntent, "Share via"))
//        }
//
        binding.backVideo.setOnClickListener {
            onBackPressed()
        }

//      binding.videoView1.setVideoPath(R.raw.video1.toString())
//      binding.videoView1.start()

//        val fileName = R.raw.video1.toString()
//
//        var uri = Uri.parse(fileName)
//        binding.videoView1.setVideoURI(uri)
//        binding.videoView1.start()

    }
}