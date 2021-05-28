package site.yoonsang.agetoeatschoollunch.model


import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("mealServiceDietInfo")
    val mealResponseInfo: List<MealResponseInfo>?
)