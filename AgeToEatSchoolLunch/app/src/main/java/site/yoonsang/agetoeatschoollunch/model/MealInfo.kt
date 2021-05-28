package site.yoonsang.agetoeatschoollunch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MealInfo(
    @SerializedName("CAL_INFO")
    val calInfo: String,
    @SerializedName("DDISH_NM")
    val mealMenu: String,
    @SerializedName("MLSV_YMD")
    val mealDate: String,
    @SerializedName("MMEAL_SC_NM")
    val mealType: String,
    @SerializedName("NTR_INFO")
    val ntrInfo: String,
    @SerializedName("ORPLC_INFO")
    val mealOrigin: String,
) : Serializable