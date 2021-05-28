package site.yoonsang.agetoeatschoollunch.repository

import retrofit2.Response
import site.yoonsang.agetoeatschoollunch.BuildConfig
import site.yoonsang.agetoeatschoollunch.model.SchoolResponse
import site.yoonsang.agetoeatschoollunch.network.NeisApi
import site.yoonsang.agetoeatschoollunch.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepository @Inject constructor(
    private val neisApi: NeisApi
) {

    suspend fun getSchoolInfo(schoolName: String): Response<SchoolResponse> =
        neisApi.getSchoolInfo(
            key = BuildConfig.KEY,
            type = Constants.TYPE,
            index = Constants.STARTING_PAGE_INDEX,
            size = Constants.ITEM_MEMBERS_IN_PAGE,
            schoolName = schoolName
        )
}