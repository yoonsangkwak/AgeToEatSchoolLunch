package site.yoonsang.agetoeatschoollunch.src.register.models


import com.google.gson.annotations.SerializedName

data class InfoHeadResult(
    @SerializedName("CODE")
    val code: String,
    @SerializedName("MESSAGE")
    val message: String
)