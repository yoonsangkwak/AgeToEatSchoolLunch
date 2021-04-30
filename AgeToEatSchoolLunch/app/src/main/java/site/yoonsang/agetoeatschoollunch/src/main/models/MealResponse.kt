package site.yoonsang.agetoeatschoollunch.src.main.models


import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("mealServiceDietInfo")
    val mealResponseInfo: List<MealResponseInfo>
)