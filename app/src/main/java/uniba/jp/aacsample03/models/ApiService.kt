package uniba.jp.aacsample03.models

import io.reactivex.Single
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiService {

    @GET("forecast/webservice/json/v1")
    fun getData(@Query("city") city: String): Single<ResponseBody>

    @GET("forecast/webservice/json/v1")
    fun getDataAsync(@Query("city") city: String): Deferred<ResponseBody>
}