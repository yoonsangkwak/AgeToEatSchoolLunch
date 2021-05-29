package site.yoonsang.agetoeatschoollunch.repository

import androidx.lifecycle.LiveData
import retrofit2.Response
import site.yoonsang.agetoeatschoollunch.BuildConfig
import site.yoonsang.agetoeatschoollunch.database.AllergyDao
import site.yoonsang.agetoeatschoollunch.model.Allergy
import site.yoonsang.agetoeatschoollunch.model.MealResponse
import site.yoonsang.agetoeatschoollunch.network.NeisApi
import site.yoonsang.agetoeatschoollunch.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val neisApi: NeisApi,
    private val allergyDao: AllergyDao
) {

    suspend fun getMealResponse(
        officeCode: String,
        schoolCode: String,
        mealDate: String
    ): Response<MealResponse> =
        neisApi.getMealResponse(
            key = BuildConfig.KEY,
            type = Constants.TYPE,
            index = Constants.STARTING_PAGE_INDEX,
            size = Constants.ITEM_MEMBERS_IN_PAGE,
            officeCode, schoolCode, mealDate
        )

    suspend fun insert(allergy: Allergy) {
        allergyDao.insert(allergy)
    }

    fun getAllergies(): LiveData<List<Allergy>> {
        return allergyDao.getAllergies()
    }

    suspend fun delete(allergy: Allergy) {
        allergyDao.delete(allergy)
    }
}