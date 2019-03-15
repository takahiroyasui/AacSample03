package uniba.jp.aacsample03.models

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiClient {

    private val apiServiceRx: ApiService = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .baseUrl("http://weather.livedoor.com/")
        .build()
        .create(ApiService::class.java)

    private val apiServiceCo: ApiService = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .baseUrl("http://weather.livedoor.com/")
        .build()
        .create(ApiService::class.java)


    fun getData(city: String): Single<ResponseBody> = apiServiceRx.getData(city)

    fun getDataAsync(city: String): Deferred<ResponseBody> = apiServiceCo.getDataAsync(city)
}