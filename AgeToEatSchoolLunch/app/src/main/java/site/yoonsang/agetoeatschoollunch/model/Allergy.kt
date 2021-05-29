package site.yoonsang.agetoeatschoollunch.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import site.yoonsang.agetoeatschoollunch.util.Constants

@Entity(tableName = Constants.ALLERGY_TABLE)
@Parcelize
class Allergy(
    @PrimaryKey
    val id: String,
    val name: String,
    var checked: Boolean = false
): Parcelable