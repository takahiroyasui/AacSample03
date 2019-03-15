package uniba.jp.aacsample03.viewmodel

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import timber.log.Timber
import uniba.jp.aacsample03.models.network.ApiClient


class MainActivityViewModel : ViewModel(), LifecycleObserver {

    private val viewModelJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val text : MutableLiveData<String> = MutableLiveData()
    private val apiClient = ApiClient()
    private val compositeDisposable = CompositeDisposable()

    fun onClick1() {
        apiClient.getData("130010")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text.postValue(it.description.text) }, Timber::e)
            .addTo(compositeDisposable)
    }

    fun onClick2() {
        scope.launch {
            val res = apiClient.getDataAsync("130010")
            text.postValue(res.description.text)
        }
    }

    fun onClick3() {
        text.postValue("")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
        viewModelJob.cancel()
        compositeDisposable.dispose()
    }
}