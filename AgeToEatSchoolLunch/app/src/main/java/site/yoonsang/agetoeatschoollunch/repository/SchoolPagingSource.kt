package site.yoonsang.agetoeatschoollunch.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import site.yoonsang.agetoeatschoollunch.BuildConfig
import site.yoonsang.agetoeatschoollunch.model.SchoolInfo
import site.yoonsang.agetoeatschoollunch.network.NeisApi
import site.yoonsang.agetoeatschoollunch.util.Constants.ITEM_MEMBERS_IN_PAGE
import site.yoonsang.agetoeatschoollunch.util.Constants.SHOW_STARTING_PAGE_INDEX
import site.yoonsang.agetoeatschoollunch.util.Constants.TYPE
import java.io.IOException

class SchoolPagingSource(
    private val neisApi: NeisApi,
    private val schoolName: String
) : PagingSource<Int, SchoolInfo>() {

    override fun getRefreshKey(state: PagingState<Int, SchoolInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SchoolInfo> {
        val position = params.key ?: SHOW_STARTING_PAGE_INDEX
        return try {
            val response = neisApi.getSchoolInfo(
                key = BuildConfig.KEY,
                type = TYPE,
                index = SHOW_STARTING_PAGE_INDEX,
                size = ITEM_MEMBERS_IN_PAGE,
                schoolName = schoolName
            )

            val schoolInfo = response.schoolResponseInfo!![1].schoolInfo

            LoadResult.Page(
                data = schoolInfo,
                prevKey = if (position == SHOW_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (schoolInfo.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}