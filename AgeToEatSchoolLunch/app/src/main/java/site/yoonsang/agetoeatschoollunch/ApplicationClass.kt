package site.yoonsang.agetoeatschoollunch

import android.app.Application
import android.content.SharedPreferences

class ApplicationClass: Application() {

    companion object {
        lateinit var sSharedPref: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        sSharedPref = applicationContext.getSharedPreferences("AgeToEatSchoolLunch", MODE_PRIVATE)
    }
}