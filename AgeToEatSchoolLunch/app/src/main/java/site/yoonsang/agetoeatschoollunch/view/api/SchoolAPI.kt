package site.yoonsang.agetoeatschoollunch.view.api

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.agetoeatschoollunch.view.models.SchoolResponse

interface SchoolAPI {

    @GET("/hub/schoolInfo")
    fun getSchoolResponse(
        @Query("KEY") key: String,
        @Query("Type") type: String,
        @Query("pIndex") index: Int,
        @Query("pSize") size: Int,
        @Query("SCHUL_NM") schoolName: String? = null
    ): Observable<SchoolResponse>

    companion object {
        private const val BASE_URL = "https://open.neis.go.kr/"

        fun create(): SchoolAPI {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SchoolAPI::class.java)
        }
    }
}