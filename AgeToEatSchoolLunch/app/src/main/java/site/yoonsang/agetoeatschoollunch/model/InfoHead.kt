package site.yoonsang.agetoeatschoollunch.model


import com.google.gson.annotations.SerializedName

data class InfoHead(
    @SerializedName("list_total_count")
    val listTotalCount: Int,
    @SerializedName("RESULT")
    val rESULT: InfoHeadResult
)