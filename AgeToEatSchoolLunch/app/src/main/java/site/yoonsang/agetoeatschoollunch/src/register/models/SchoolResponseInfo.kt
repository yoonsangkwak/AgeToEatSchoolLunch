package site.yoonsang.agetoeatschoollunch.src.register.models


import com.google.gson.annotations.SerializedName

data class SchoolResponseInfo(
    @SerializedName("head")
    val infoHead: List<InfoHead>,
    @SerializedName("row")
    val schoolInfo: List<SchoolInfo>
)