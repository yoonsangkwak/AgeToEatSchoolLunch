package site.yoonsang.agetoeatschoollunch.src.register

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.agetoeatschoollunch.BuildConfig
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.src.register.models.SchoolResponse

class RegisterPresenter(val view: RegisterContract.View) {

    fun tryGetSchoolResponse(schoolName: String) {
        val retrofit = ApplicationClass.sRetrofit.create(RegisterContract.Presenter::class.java)
        retrofit.getSchoolResponse(
            BuildConfig.KEY,
            "json",
            1,
            100,
            schoolName
        ).enqueue(object : Callback<SchoolResponse> {
            override fun onResponse(
                call: Call<SchoolResponse>,
                response: Response<SchoolResponse>
            ) {
                if (response.body() != null) {
                    view.getSchoolResponseSuccess(response.body() as SchoolResponse)
                } else {
                    view.getSchoolResponseFailure(response.message())
                }
            }

            override fun onFailure(call: Call<SchoolResponse>, t: Throwable) {
                view.getSchoolResponseFailure(t.message ?: "통신 오류")
            }
        })
    }
}