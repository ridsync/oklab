package com.okitoki.okchat.ui.sign

import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.annotation.LayoutRes
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.CredentialsOptions
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.GoogleApiClient
import com.mikepenz.aboutlibraries.LibsBuilder
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivityLoginBinding
import com.okitoki.okchat.ui.base.BaseActivity
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.getViewModel


/**
 * Created by okc on 2019-04-01.
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    lateinit var mGoogleApiClient : GoogleApiClient

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        binding.acitivty = this

        /**
         *  DataStore with Flow Example
         */
//        CoroutineScope(Main).launch {
//            readPrefDataStore()
//            incrementCounterPrefDataStore()
//
//            readProtoDataStore()
//        }

        registSMSRetriever()

        getSignature()

        ininGoogleAPI()
    }

    private fun getSignature() {
        val signatureHelper = AppSignatureHelper(this)
        val appSignatures = signatureHelper.appSignatures
        Logger.d("[SMS] appSignatture = " + appSignatures[0])
    }
    private fun registSMSRetriever(){
        val client = SmsRetriever.getClient(this /* context */)
        val task = client.startSmsRetriever()
        task.addOnSuccessListener {
            // Successfully started retriever, expect broadcast intent
            // ...
            Logger.d("[SMS] addOnSuccessListener $it")
        }

        task.addOnFailureListener {
            // Failed to start retriever, inspect Exception for more details
            // ...
            Logger.d("[SMS] addOnFailureListener $it")
        }
    }

    /**
     * 이건 안되는것 같다. ㅠ,ㅠ 게다가 Deprecated
     */
    private fun ininGoogleAPI() {

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                override fun onConnected(bundle: Bundle?) {

                }

                override fun onConnectionSuspended(i: Int) {}
            })
            .enableAutoManage(this, null)
            .addApi(Auth.CREDENTIALS_API)
            .build()

        mGoogleApiClient.connect()
    }

    fun onClickPhoneTest(view: View){
//        getPhonNumberTest()
//        pickerTest()
        aboutLibrariesTest()
    }

    private fun aboutLibrariesTest(){
        LibsBuilder()
            .withActivityTitle("오픈소스 테스")
            .withAboutAppName(getString(R.string.app_name))
            .withAboutIconShown(true)
            .withAboutVersionShown(true)
            .withAutoDetect(true)
            .withLicenseShown(true)
            .start(this) // start the activity
    }

    private fun pickerTest(){
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPhoto, 2002)
    }

    /**
     *  기기별로 안되기도 하고 되기도 하고 일단 실패했음.
     */
    fun getPhonNumberTest() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()
        val options = CredentialsOptions.Builder()
            .forceEnableSaveDialog()
            .build()

//        val intent = Credentials.getClient(this, options).getHintPickerIntent(hintRequest)
//        val intent: PendingIntent =
//            Credentials.getClient(this).getHintPickerIntent(hintRequest)
        val intent = Auth.CredentialsApi.getHintPickerIntent(
            mGoogleApiClient, hintRequest
        )
        try {
            startIntentSenderForResult(intent.intentSender, 10102, null, 0, 0, 0, Bundle())
        } catch (e: SendIntentException) {
            Logger.d("[AuthGoogle] SendIntentException $e")
        }
    }

    fun getHintPhoneNumber() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()
        val mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest)
        try {
            startIntentSenderForResult(mIntent.intentSender, 10102, null, 0, 0, 0)
        } catch (e: SendIntentException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Logger.d("[AuthGoogle] onActivityResult resultCode $resultCode / = $data")
        when (requestCode) {
            10102 ->                 // Obtain the phone number from the result
                if (resultCode == RESULT_OK) {
                    val credential: Credential = data?.getParcelableExtra(Credential.EXTRA_KEY)!!
                    // credential.getId();  <-- will need to process phone number string
                    Logger.d("[AuthGoogle] credential = $credential")
                }
        }
    }

    suspend fun readPrefDataStore() {
        val dataStore: DataStore<Preferences> = createDataStore(
            name = "settings"
        )
        val counter_test = preferencesKey<Int>("counter_test")
        val exampleCounterFlow: Flow<Int> = dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[counter_test] ?: 0
            }
    }

    suspend fun incrementCounterPrefDataStore(){
        val dataStore: DataStore<Preferences> = createDataStore(
            name = "settings"
        )
        val counter_test = preferencesKey<Int>("counter_test")

        dataStore.edit { settings ->
            val currentCounterValue = settings[counter_test] ?: 0
            settings[counter_test] = currentCounterValue + 1
        }
    }

//    object SettingsSerializer : Serializer<Settings> {
//        override fun readFrom(input: InputStream): Settings {
//            try {
//                return Settings.parseFrom(input)
//            } catch (exception: InvalidProtocolBufferException) {
//                throw CorruptionException("Cannot read proto.", exception)
//            }
//        }
//
//        override fun writeTo(
//            t: Settings,
//            output: OutputStream
//        ) = t.writeTo(output)
//    }
//
//    val settingsDataStore: DataStore<Settings> = context.createDataStore(
//        fileName = "settings.pb",
//        serializer = SettingsSerializer
//    )
//
//    suspend fun readProtoDataStore() {
//        settingsDataStore.updateData { currentSettings ->
//            currentSettings.toBuilder()
//                .setExampleCounter(currentSettings.exampleCounter + 1)
//                .build()
//        }
//    }


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
        if(uri.lastPathSegment?.contains("mp3") == true || uri.lastPathSegment?.contains("mp4") == true){
            return ProgressiveMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent)).createMediaSource(
                uri
            )
//        }else if(uri.getLastPathSegment().contains("m3u8")){
//            return HlsMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent)).createMediaSource(uri)
        }else{
            return ProgressiveMediaSource.Factory(DefaultDataSourceFactory(this, userAgent)).createMediaSource(
                uri
            )
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
