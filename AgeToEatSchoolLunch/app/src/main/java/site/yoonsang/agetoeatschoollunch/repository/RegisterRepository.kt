package site.yoonsang.agetoeatschoollunch.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import site.yoonsang.agetoeatschoollunch.BuildConfig
import site.yoonsang.agetoeatschoollunch.api.SchoolAPI
import site.yoonsang.agetoeatschoollunch.models.SchoolResponse

class RegisterRepository(application: Application) {

    private val api = SchoolAPI.create()
    private var schoolData: MutableLiveData<SchoolResponse> = MutableLiveData()

    fun getSchools(schoolName: String?): Observable<SchoolResponse> = api
        .getSchoolResponse(
            BuildConfig.KEY,
            "json",
            1,
            20,
            schoolName
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}