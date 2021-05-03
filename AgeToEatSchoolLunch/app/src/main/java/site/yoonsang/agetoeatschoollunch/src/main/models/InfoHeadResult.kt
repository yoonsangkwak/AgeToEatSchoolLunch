package site.yoonsang.agetoeatschoollunch.src.main.models


import com.google.gson.annotations.SerializedName

data class InfoHeadResult(
    @SerializedName("CODE")
    val cODE: String,
    @SerializedName("MESSAGE")
    val mESSAGE: String
)