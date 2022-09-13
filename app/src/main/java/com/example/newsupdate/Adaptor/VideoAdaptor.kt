package com.example.newsupdate.Adaptor

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsupdate.ModelClass
import com.example.newsupdate.R
import com.example.newsupdate.Screen.Video_Screen


class VideoAdaptor(val videoItems1: Video_Screen, videoItems: List<ModelClass>) :
    RecyclerView.Adapter<VideoAdaptor.ViewData>() {

    private val mVideoItems: List<ModelClass>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {

        return ViewData(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.videoview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.setVideoData(mVideoItems[position])

//        holder.mVideoView.setOnCompletionListener {
//            if ()
//        }
    }

    override fun getItemCount(): Int {
        return mVideoItems.size
    }

    class ViewData(itemView: View) : NewsAdaptor.ViewHolder(itemView) {
        var mVideoView: VideoView
        var mProgressBar: ProgressBar
        var stop_txt: TextView
        var videoView: VideoView
        var mute_unmute_img: ImageView
        var b = 0

        fun setVideoData(videoItem: ModelClass) {

            mVideoView.setVideoPath(videoItem.videoUrl)

            mVideoView.setOnPreparedListener { mp ->
                mProgressBar.visibility = View.GONE

                videoView.setOnClickListener {
                    if (mp.isPlaying) {
                        if (b == 0) {
                            mp.setVolume(0f, 0f)
                            stop_txt.text = "Mute"
                            mute_unmute_img.setImageResource(R.drawable.mute)
                            b = 1
                        } else {
                            mp.setVolume(1F, 1F)
                            stop_txt.text = "Unmute"

                            mute_unmute_img.setImageResource(R.drawable.unmute)
                            b = 0
                        }
                    }
                }

                videoView.setOnTouchListener(object : View.OnTouchListener {
                    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                        if (p1?.actionMasked == MotionEvent.ACTION_MOVE) {
                            mp.pause()
                        } else {
                            mp.start()
                        }
                        return false
                    }
                })

                mp.start()
                val videoRatio = mp.videoWidth / mp.videoHeight.toFloat()
                val screenRatio = mVideoView.width / mVideoView.height.toFloat()
                val scale = screenRatio
                if (scale >= 1f) {
                    mVideoView.scaleX = scale
                } else {
                    mVideoView.scaleY = 1f / scale
                }
            }
            mVideoView.setOnCompletionListener { mp -> mp.start() }
        }

        init {
            mVideoView = itemView.findViewById(R.id.videoView)
            mProgressBar = itemView.findViewById(R.id.progressBar)
            stop_txt = itemView.findViewById(R.id.stop_txt)
            videoView = itemView.findViewById(R.id.videoView)
            mute_unmute_img = itemView.findViewById(R.id.mute_unmute_img)
        }
    }

    init {
        mVideoItems = videoItems
    }
}