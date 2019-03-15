package uniba.jp.aacsample03.models.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import uniba.jp.aacsample03.models.weather.Result


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


    fun getData(city: String): Single<Result> = apiServiceRx.getData(city)

    suspend fun getDataAsync(city: String): Result = apiServiceCo.getDataAsync(city).await()
}