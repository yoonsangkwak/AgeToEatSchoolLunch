package site.yoonsang.agetoeatschoollunch.repository

import androidx.lifecycle.LiveData
import site.yoonsang.agetoeatschoollunch.database.AllergyDao
import site.yoonsang.agetoeatschoollunch.model.Allergy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllergyRepository @Inject constructor(
    private val allergyDao: AllergyDao
) {

    suspend fun insert(allergy: Allergy) {
        allergyDao.insert(allergy)
    }

    suspend fun update(allergy: Allergy) {
        allergyDao.update(allergy)
    }

    fun getAllergies(): LiveData<List<Allergy>> {
        return allergyDao.getAllergies()
    }
}