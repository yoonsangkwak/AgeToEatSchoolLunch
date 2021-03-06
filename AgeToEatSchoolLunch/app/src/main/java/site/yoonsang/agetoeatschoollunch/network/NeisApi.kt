package site.yoonsang.agetoeatschoollunch.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.agetoeatschoollunch.model.MealResponse
import site.yoonsang.agetoeatschoollunch.model.SchoolResponse

interface NeisApi {

    companion object {
        const val BASE_URL = "https://open.neis.go.kr/"
    }

    @GET("/hub/schoolInfo")
    suspend fun getSchoolInfo(
        @Query("KEY") key: String,
        @Query("Type") type: String,
        @Query("pIndex") index: Int,
        @Query("pSize") size: Int,
        @Query("SCHUL_NM") schoolName: String? = null
    ): Response<SchoolResponse>

    @GET("/hub/mealServiceDietInfo")
    suspend fun getMealResponse(
        @Query("KEY") key: String,
        @Query("Type") type: String,
        @Query("pIndex") index: Int,
        @Query("pSize") size: Int,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("MLSV_YMD") mealDate: String? = null
    ): Response<MealResponse>
}