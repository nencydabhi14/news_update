package com.example.newsfire10

import android.R
import android.content.Context
import android.widget.ImageView
import com.example.newsupdate.sharp.Sharp
import okhttp3.*
import java.io.IOException
import java.io.InputStream

class Utils {
	
	// on below line we are creating a variable for http client.
	private var httpClient: OkHttpClient? = null

	// on below line we are creating a function to load the svg from the url.
	// in below method we are specifying parameters as context,
	// url for the image and image view.
	fun fetchSVG(context: Context, url: String, target: ImageView) {
		// on below line we are checking
		// if http client is null
		if (httpClient == null) {
			// if it is null on below line
			// we are initializing our http client.
			httpClient =
				OkHttpClient.Builder().cache(Cache(context.cacheDir, 5 * 1024 * 1014) as Cache)
					.build() as OkHttpClient
		}
		
		// on below line we are creating a variable for our request and initialing it.
		var request: Request = Request.Builder().url(url).build()
		
		// on below line we are making a call to the http request on below lnie.
		httpClient!!.newCall(request).enqueue(object : Callback {

			override fun onFailure(call: Call, e: IOException) {
				target.setImageResource(R.drawable.stat_notify_error)
			}

			override fun onResponse(call: Call, response: Response) {
				val stream: InputStream = response.body!!.byteStream()
				Sharp.loadInputStream(stream).into(target)
				stream.close()
			}
		})
	}
}
