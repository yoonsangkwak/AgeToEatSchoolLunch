package site.yoonsang.agetoeatschoollunch.util

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(private val preferences: SharedPreferences) {

    fun getSchoolName(): String? {
        return preferences.getString(Constants.SCHOOL_NAME, "")
    }

    fun setSchoolName(schoolName: String) {
        val editor = preferences.edit()
        editor.putString(Constants.SCHOOL_NAME, schoolName)
        editor.apply()
    }
}