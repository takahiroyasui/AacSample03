package uniba.jp.aacsample03.models.network

import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.*
import uniba.jp.aacsample03.models.weather.Result


interface ApiService {

    @GET("forecast/webservice/json/v1")
    fun getData(@Query("city") city: String): Single<Result>

    @GET("forecast/webservice/json/v1")
    fun getDataAsync(@Query("city") city: String): Deferred<Result>
}