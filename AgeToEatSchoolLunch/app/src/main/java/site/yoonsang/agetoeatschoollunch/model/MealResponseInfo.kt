package site.yoonsang.agetoeatschoollunch.model

import com.google.gson.annotations.SerializedName

data class MealResponseInfo(
    @SerializedName("head")
    val infoHead: List<InfoHead>,
    @SerializedName("row")
    val mealInfo: List<MealInfo>
)