package site.yoonsang.agetoeatschoollunch.src.register.models


import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("schoolInfo")
    val schoolResponseInfo: List<SchoolResponseInfo>?
)