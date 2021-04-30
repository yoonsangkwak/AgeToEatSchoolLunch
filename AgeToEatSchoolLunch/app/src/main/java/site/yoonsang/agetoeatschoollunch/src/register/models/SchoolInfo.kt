package site.yoonsang.agetoeatschoollunch.src.register.models


import com.google.gson.annotations.SerializedName

data class SchoolInfo(
    @SerializedName("head")
    val schoolInfoHead: List<SchoolInfoHead>,
    @SerializedName("row")
    val schoolInfoRow: List<SchoolInfoRow>
)