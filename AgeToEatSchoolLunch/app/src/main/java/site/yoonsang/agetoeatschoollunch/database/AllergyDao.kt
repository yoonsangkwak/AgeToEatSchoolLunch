package site.yoonsang.agetoeatschoollunch.database

import androidx.lifecycle.LiveData
import androidx.room.*
import site.yoonsang.agetoeatschoollunch.model.Allergy

@Dao
interface AllergyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allergy: Allergy)

    @Query("select * from allergy_table order by id asc")
    fun getAllergies(): LiveData<List<Allergy>>

    @Update
    suspend fun update(allergy: Allergy)
}