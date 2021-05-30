package site.yoonsang.agetoeatschoollunch.model

import com.google.gson.annotations.SerializedName

data class SchoolResponseInfo(
    @SerializedName("head")
    val infoHead: List<InfoHead>,
    @SerializedName("row")
    val schoolInfo: List<SchoolInfo>
)