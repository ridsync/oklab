package com.okitoki.okchat.ui
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.StorageAccessLevel
import com.amplifyframework.storage.options.StorageUploadFileOptions
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivityAwsAmplifyBinding
import com.okitoki.okchat.ui.base.BaseActivity
import com.orhanobut.logger.Logger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


/**
 * Created by okc on 2020-12-20.
 */
class AwsAmplifyActivity  : BaseActivity<ActivityAwsAmplifyBinding>() {

//    private val authViewModel: AuthViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_aws_amplify

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        Logger.d("App Version = %s", binding.tvVersion.text.toString())

        Amplify.Auth.fetchAuthSession(
            { result -> Logger.i("[AWS] AmplifyQuickstart %s", result.toString()) },
            { error -> Logger.e("[AWS] AmplifyQuickstart %s", error.toString()) }
        )
//        loginAmplify()
//        initAWSMobileClient()
    }

    private fun loginAmplify() {
        // 유저아이디, 패스워드로 가입하면 Confirm과정으로 유저 확인이 ㅂ필요하다 .
        Amplify.Auth.signUp(
            "test1",
            "Password123",
            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), "my@email.com").build(),
            { result -> Logger.i("[AWS] AuthQuickStart signUp Result: $result")

            },
            { error -> Logger.e("[AWS] AuthQuickStart Sign up failed %s", error) }
        )

        Amplify.Auth.signIn(
            "test1",
            "Password123",
            { result -> Logger.i("[AWS] AuthQuickStart signIn Result: $result") },
            { error -> Logger.e("[AWS] AuthQuickStart signIn failed %s", error) }
        )

        Amplify.Auth.fetchAuthSession(
            { result -> Logger.i("[AWS] AmplifyQuickstart %s", result.toString()) },
            { error -> Logger.e("[AWS] AmplifyQuickstart %s", error.toString()) }
        )
    }

    /**
     * AmazonS3Client 방식으로 사용시 클라이언트 초기화
     */
    private fun initAWSMobileClient() {
        AWSMobileClient.getInstance()
            .initialize(applicationContext, object : Callback<UserStateDetails> {
                override fun onResult(userStateDetails: UserStateDetails) {
                    Logger.i("[AWS] INIT AWSMobileClient onResult:  User State " + userStateDetails.userState)
                }

                override fun onError(e: java.lang.Exception) {
                    Logger.e("[AWS] INIT AWSMobileClient Initialization error.", e)
                }
            }
            )
    }

    /**
     * 업로드 방법 3가지가 존재 어떤 방법이 적합한가 ?
     */
    fun onClickUploadS3(view: View){
        uploadFileByAmplify()
//        uploadWithAWSMobileClient()
//        uploadWithCredentialsProvider()
    }

    private fun uploadFileByAmplify() {
        // TODO "filepath/useridx_time" java.util.Base64 or md5?
        val key = "filepath/key3public"
        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
        exampleFile.writeText("Example file contents")
        val options = StorageUploadFileOptions.builder()
            .accessLevel(StorageAccessLevel.PUBLIC)
            .build()

        Amplify.Storage.uploadFile(
            key,
            exampleFile,
//            { progress -> Logger.i("[AWS] Amplify Fraction completed : ${progress.fractionCompleted}") },
            StorageUploadFileOptions.defaultInstance(),
            { result -> Logger.i("[AWS] Amplify Successfully uploaded : ${result.key}") },
            { error -> Logger.e("[AWS] Amplify Upload failed %s", error) }
        )
        // 다운로드 샘플
//        Amplify.Storage.downloadFile(
//            key,
//            File("${applicationContext.filesDir}/download.txt"),
//            { result -> Logger.i("MyAmplifyApp", "[AWS] Successfully downloaded: ${result.file.name}") },
//            { error -> Logger.e("MyAmplifyApp", "[AWS] Download Failure", error) }
//        )
    }

    private fun uploadWithAWSMobileClient() {

        val transferUtility = TransferUtility.builder()
            .context(applicationContext)
            .awsConfiguration(AWSMobileClient.getInstance().configuration)
            .s3Client(AmazonS3Client(AWSMobileClient.getInstance(), Region.getRegion(Regions.AP_NORTHEAST_2)))
            .build()

        val file = File(applicationContext.filesDir, "sample22.txt")
        try {
            val writer = BufferedWriter(FileWriter(file))
            writer.append("Howdy World!")
            writer.close()
        } catch (e: Exception) {
            e.message?.let { Logger.e(it) }
        }

        val uploadObserver = transferUtility.upload(
            "public/sample22.txt",
            File(applicationContext.filesDir, "sample22.txt"),
            CannedAccessControlList.PublicRead
        )

        // Attach a listener to the observer to get state update and progress notifications
        uploadObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (TransferState.COMPLETED == state) {
                    // Handle a completed upload.
                    Logger.d("[AWS] MobileClient Upload Completed!")
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                val percentDonef = bytesCurrent.toFloat() / bytesTotal.toFloat() * 100
                val percentDone = percentDonef.toInt()
                Logger.d(
                    "[AWS] MobileClient ID:" + id + " bytesCurrent: " + bytesCurrent
                            + " bytesTotal: " + bytesTotal + " " + percentDone + "%"
                )
            }

            override fun onError(id: Int, ex: Exception) {
                // Handle errors
                Logger.d("[AWS] MobileClient  Upload onError! $id ${ex.message}")
            }
        })

        // If you prefer to poll for the data, instead of attaching a
        // listener, check for the state and progress in the observer.

        // If you prefer to poll for the data, instead of attaching a
        // listener, check for the state and progress in the observer.
        if (TransferState.COMPLETED == uploadObserver.state) {
            // Handle a completed upload.
        }

        Logger.d("[AWS]  MobileClient Bytes Transferred: " + uploadObserver.bytesTransferred)
        Logger.d("[AWS]  MobileClient Bytes Total: " + uploadObserver.bytesTotal)
    }

    private fun uploadWithCredentialsProvider() {

        // Cognito 샘플 코드. CredentialsProvider 객체 생성
        val credentialsProvider = CognitoCachingCredentialsProvider(
            applicationContext,
            "ap-northeast-2:fdfe5696-76f3-4aa5-96d9-4df08cffe28a", // 자격 증명 풀 ID : okds3client
//            "ap-northeast-2:a60071ef-688a-4e42-8381-b1038f7c2988", // 자격 증명 풀 ID : oklabac9ca473_identitypool_ac9ca473__dev
            Regions.AP_NORTHEAST_2 // 리전
        )
        // 반드시 호출해야 한다.
        TransferNetworkLossHandler.getInstance(applicationContext)

        // TransferUtility 객체 생성
        val transferUtility = TransferUtility.builder()
            .context(applicationContext)
            .defaultBucket("okd-profile-photo") // 디폴트 버킷 이름.
//            .defaultBucket("oklab59bd32a4da71437f91f2e6ec1c0b0fde172210-dev") // 디폴트 버킷 이름.
            .s3Client(AmazonS3Client(credentialsProvider, Region.getRegion(Regions.AP_NORTHEAST_2)))
            .build()

        val key = "filepath/congnito/keypublic"
        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
        exampleFile.writeText("Example file contentsssssss")
        val downloadObserver = transferUtility.upload(
            key,
            exampleFile,
            CannedAccessControlList.PublicRead
        )

        // 다운로드 과정을 알 수 있도록 Listener를 추가할 수 있다.
        downloadObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    Logger.d("[AWS] CredentialsProvider Upload Completed!")
                }
            }

            override fun onProgressChanged(id: Int, current: Long, total: Long) {
                try {
                    val done = (((current.toDouble() / total) * 100.0).toInt()) //as Int
                    Logger.d("[AWS] CredentialsProvider Upload - - ID: $id, percent done = $done")
                } catch (e: Exception) {
                    Logger.d("[AWS] CredentialsProvider Trouble calculating progress percent", e)
                }
            }

            override fun onError(id: Int, ex: Exception) {
                Logger.d("[AWS] CredentialsProvider Upload ERROR - - ID: $id - - EX: ${ex.message.toString()}")
            }
        })
    }

}