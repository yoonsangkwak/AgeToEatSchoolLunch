package site.yoonsang.agetoeatschoollunch.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import site.yoonsang.agetoeatschoollunch.network.NeisApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepository @Inject constructor(
    private val neisApi: NeisApi
) {

    fun getSchoolInfo(schoolName: String) =
        Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SchoolPagingSource(neisApi, schoolName)
            }
        ).liveData
}