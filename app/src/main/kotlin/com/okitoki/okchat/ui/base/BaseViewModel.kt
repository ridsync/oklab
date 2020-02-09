package com.okitoki.okchat.ui.base
import androidx.lifecycle.ViewModel
import com.okitoki.okchat.data.net.response.BaseResponse
import com.okitoki.okchat.data.net.response.RepositoriesResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    // 일회성 이벤트를 만들어 내는 라이브 이벤트
    // 뷰는 이러한 이벤트를 바인딩하고 있다가, 적절한 상황이 되면 액티비티를 호출하거나 스낵바를 만듬
//    private val snackbarMessage = SnackbarMessage()
//    private val snackbarMessageString = SnackbarMessageString()

    /**
     * RxJava 의 observing을 위한 부분.
     * addDisposable을 이용하여 추가하기만 하면 된다
     */
    private val compositeDisposable = CompositeDisposable()

    fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    /**
     *  Response 처리
     *  1) BaseResponse 데이터 반환
     *  2) 코드상 에러처리 및 에러 콜백
     */
    protected fun <T : BaseResponse> processBaseResponse(response: Response<T>) : T? {
        if(response.isSuccessful ){
            response.body()?.let {
                // TODO 서버 공통 에러 콜백 처리.

                    return it
            }
        }
        // TODO 네트워크 콜백 처리.
        doOnErrorCallback()
        return null
    }

    /**
     *  retrfit API errorCallback 처리
     *  팝업 or 스낵바 or 뷰표시 등의 에러 처리.
     */
    fun doOnErrorCallback(){

    }

    /**
     * 스낵바를 보여주고 싶으면 viewModel 에서 이 함수를 호출
     */
//    fun showSnackbar(stringResourceId:Int) {
//        snackbarMessage.value = stringResourceId
//    }
//    fun showSnackbar(str:String){
//        snackbarMessageString.value = str
//    }
//
//    /**
//     * BaseActivity 에서 쓰는 함수
//     */
//    fun observeSnackbarMessage(lifeCycleOwner: LifecycleOwner, ob:(Int) -> Unit){
//        snackbarMessage.observe(lifeCycleOwner, ob)
//    }
//    fun observeSnackbarMessageStr(lifeCycleOwner: LifecycleOwner, ob:(String) -> Unit) {
//        snackbarMessageString.observe(lifeCycleOwner, ob)
//    }

}