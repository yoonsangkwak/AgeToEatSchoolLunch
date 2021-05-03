package site.yoonsang.agetoeatschoollunch.src.main.models

import com.google.gson.annotations.SerializedName

data class MealResponseInfo(
    @SerializedName("head")
    val infoHead: List<InfoHead>,
    @SerializedName("row")
    val mealInfo: List<MealInfo>
)