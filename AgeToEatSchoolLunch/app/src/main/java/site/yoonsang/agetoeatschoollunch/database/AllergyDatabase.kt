package site.yoonsang.agetoeatschoollunch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import site.yoonsang.agetoeatschoollunch.model.Allergy

@Database(entities = [Allergy::class], version = 1, exportSchema = false)
abstract class AllergyDatabase: RoomDatabase() {
    abstract fun allergyDao(): AllergyDao
}