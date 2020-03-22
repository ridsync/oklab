package com.okitoki.okchat.ui.sign

import android.net.Uri
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivityLoginBinding
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


/**
 * Created by okc on 2019-04-01.
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {

//        showLoading()

//        Handler().postDelayed({ hideLoading() }, 8000L)

        initializePlayer()

    }

    private fun initializePlayer(){
        if(player == null){
            player = SimpleExoPlayer.Builder(applicationContext).build()
            binding.pvExoplayer.player = player
        }
        // var video_url:String = "{url}"
        // var mediaSource: MediaSource = buildMediaSource(Uri.parse(video_url))
        var uri = RawResourceDataSource.buildRawResourceUri(R.raw.onboarding)
        var mediaSource: MediaSource = buildMediaSource(uri)

        //준비
//        player!!.seekTo(currentWindow, playbackPosition)
        player?.prepare(mediaSource, true, false)
        player?.playWhenReady = true
        player?.repeatMode = REPEAT_MODE_ALL
    }

    private fun buildMediaSource(uri: Uri) : MediaSource{
        var userAgent:String = Util.getUserAgent(this, packageName)
        if(uri.lastPathSegment.contains("mp3") || uri.lastPathSegment.contains("mp4")){
            return ProgressiveMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent)).createMediaSource(uri)
//        }else if(uri.getLastPathSegment().contains("m3u8")){
//            return HlsMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent)).createMediaSource(uri)
        }else{
            return ProgressiveMediaSource.Factory(DefaultDataSourceFactory(this, userAgent)).createMediaSource(uri)
        }
    }

    fun releasePlayer(){
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            binding.pvExoplayer.player = null
            it.release()
            player = null
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
}
