package site.yoonsang.agetoeatschoollunch.src.main

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.agetoeatschoollunch.BuildConfig
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.src.main.models.MealResponse

class MainPresenter(val view: MainContract.View) {

    fun tryGetMealResponse(officeCode: String, schoolCode: String, mealDate: String?=null) {
        val retrofit = ApplicationClass.sRetrofit.create(MainContract.Presenter::class.java)
        retrofit.getMealResponse(
            BuildConfig.KEY,
            "json",
            1,
            100,
            officeCode, schoolCode, mealDate
        ).enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.body() != null) {
                    view.getMealResponseSuccess(response.body() as MealResponse)
                } else {
                    view.getMealResponseFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                view.getMealResponseFailure(t.message ?: "통신 오류")
            }
        })
    }
}