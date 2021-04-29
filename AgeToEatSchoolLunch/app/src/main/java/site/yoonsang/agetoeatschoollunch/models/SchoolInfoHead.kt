package site.yoonsang.agetoeatschoollunch.models


import com.google.gson.annotations.SerializedName

data class SchoolInfoHead(
    @SerializedName("list_total_count")
    val listTotalCount: Int,
    @SerializedName("RESULT")
    val result: SchoolInfoHeadResult
)