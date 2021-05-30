package site.yoonsang.agetoeatschoollunch.util

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(private val preferences: SharedPreferences) {

    fun getSchoolName(): String? {
        return preferences.getString(Constants.SCHOOL_NAME, null)
    }

    fun getSchoolCode(): String? {
        return preferences.getString(Constants.SCHOOL_CODE, null)
    }

    fun getOfficeCode(): String? {
        return preferences.getString(Constants.OFFICE_CODE, null)
    }

    fun setSchoolName(schoolName: String) {
        val editor = preferences.edit()
        editor.putString(Constants.SCHOOL_NAME, schoolName)
        editor.apply()
    }

    fun setSchoolCode(schoolCode: String) {
        val editor = preferences.edit()
        editor.putString(Constants.SCHOOL_CODE, schoolCode)
        editor.apply()
    }

    fun setOfficeCode(officeCode: String) {
        val editor = preferences.edit()
        editor.putString(Constants.OFFICE_CODE, officeCode)
        editor.apply()
    }
}