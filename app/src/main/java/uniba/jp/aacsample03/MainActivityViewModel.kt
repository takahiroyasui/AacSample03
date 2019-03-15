package uniba.jp.aacsample03

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import timber.log.Timber
import uniba.jp.aacsample03.models.ApiClient


class MainActivityViewModel : ViewModel(), LifecycleObserver {

    val text : MutableLiveData<String> = MutableLiveData()
    private val apiClient = ApiClient()
    private val compositeDisposable = CompositeDisposable()


    fun onClick1() {
        apiClient.getData("130010")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text.postValue(it.string()) }, Timber::e)
            .addTo(compositeDisposable)
    }

    fun onClick2() {
        GlobalScope.launch {
            val res = apiClient.getDataAsync("130010").await()
            text.postValue(res.string())
        }
    }

    fun onClick3() {
        text.postValue("")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
        compositeDisposable.dispose()
    }
}