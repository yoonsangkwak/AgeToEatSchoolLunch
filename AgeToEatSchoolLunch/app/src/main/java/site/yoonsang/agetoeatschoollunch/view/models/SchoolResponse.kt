package site.yoonsang.agetoeatschoollunch.view.models


import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("schoolInfo")
    val schoolInfo: List<SchoolInfo>
)