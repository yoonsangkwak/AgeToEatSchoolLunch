package site.yoonsang.agetoeatschoollunch.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import site.yoonsang.agetoeatschoollunch.ApplicationClass
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        val schoolName = ApplicationClass.sSharedPref.getString("schoolName", null)

        Handler(Looper.getMainLooper()).postDelayed({
            if (schoolName == null) {
                startActivity(Intent(this, RegisterActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }, 1500)
    }
}