package site.yoonsang.agetoeatschoollunch.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.databinding.ActivitySplashBinding
import site.yoonsang.agetoeatschoollunch.src.main.MainActivity
import site.yoonsang.agetoeatschoollunch.src.register.RegisterActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val schoolName = ApplicationClass.sSharedPref.getString("schoolName", null)

        Handler(Looper.getMainLooper()).postDelayed({
            if (schoolName == null) {
                startActivity(Intent(this, RegisterActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 1500)
    }
}