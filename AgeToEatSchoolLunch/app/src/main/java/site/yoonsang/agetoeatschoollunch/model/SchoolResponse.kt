package site.yoonsang.agetoeatschoollunch.model

import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("schoolInfo")
    val schoolResponseInfo: List<SchoolResponseInfo>?
)