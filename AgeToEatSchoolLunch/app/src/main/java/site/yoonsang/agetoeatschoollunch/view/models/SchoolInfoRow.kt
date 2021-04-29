package site.yoonsang.agetoeatschoollunch.view.models

import com.google.gson.annotations.SerializedName

data class SchoolInfoRow(
    @SerializedName("ATPT_OFCDC_SC_CODE")
    val officeCode: String,
    @SerializedName("ATPT_OFCDC_SC_NM")
    val officeName: String,
    @SerializedName("ORG_RDNMA")
    val roadLocation: String,
    @SerializedName("SCHUL_NM")
    val schoolName: String,
    @SerializedName("SD_SCHUL_CODE")
    val schoolCode: String,
)