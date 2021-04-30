package site.yoonsang.agetoeatschoollunch.src.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import site.yoonsang.agetoeatschoollunch.src.main.models.MealResponse

interface MainContract {

    interface View {

        fun getMealResponseSuccess(response: MealResponse)

        fun getMealResponseFailure(message: String)
    }

    interface Presenter {

        @GET("/hub/mealServiceDietInfo")
        fun getMealResponse(
            @Query("KEY") key: String,
            @Query("Type") type: String,
            @Query("pIndex") index: Int,
            @Query("pSize") size: Int,
            @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
            @Query("SD_SCHUL_CODE") schoolCode: String,
            @Query("MLSV_YMD") mealDate: String? = null
        ): Call<MealResponse>
    }
}