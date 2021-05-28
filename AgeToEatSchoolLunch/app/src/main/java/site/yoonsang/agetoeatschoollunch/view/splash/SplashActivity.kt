package site.yoonsang.agetoeatschoollunch.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.util.SessionManager
import site.yoonsang.agetoeatschoollunch.view.main.MainActivity
import site.yoonsang.agetoeatschoollunch.view.register.RegisterActivity
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val schoolName = sessionManager.getSchoolName()

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