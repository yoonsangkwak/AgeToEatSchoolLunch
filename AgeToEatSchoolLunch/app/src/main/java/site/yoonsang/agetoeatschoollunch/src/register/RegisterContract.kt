package site.yoonsang.agetoeatschoollunch.src.register

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.agetoeatschoollunch.src.register.models.SchoolResponse

interface RegisterContract {

    interface View {
        fun getSchoolResponseSuccess(response: SchoolResponse)

        fun getSchoolResponseFailure(message: String)
    }

    interface Presenter {
        @GET("/hub/schoolInfo")
        fun getSchoolResponse(
            @Query("KEY") key: String,
            @Query("Type") type: String,
            @Query("pIndex") index: Int,
            @Query("pSize") size: Int,
            @Query("SCHUL_NM") schoolName: String? = null
        ): Call<SchoolResponse>
    }
}