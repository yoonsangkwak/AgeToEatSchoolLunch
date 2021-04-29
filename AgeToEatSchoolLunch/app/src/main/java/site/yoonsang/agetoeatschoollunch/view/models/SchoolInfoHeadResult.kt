package site.yoonsang.agetoeatschoollunch.view.models


import com.google.gson.annotations.SerializedName

data class SchoolInfoHeadResult(
    @SerializedName("CODE")
    val code: String,
    @SerializedName("MESSAGE")
    val message: String
)