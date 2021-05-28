package site.yoonsang.agetoeatschoollunch.view.main

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityMainBinding
import site.yoonsang.agetoeatschoollunch.util.SessionManager
import site.yoonsang.agetoeatschoollunch.view.allergy.AllergyActivity
import site.yoonsang.agetoeatschoollunch.view.faq.FaqActivity
import site.yoonsang.agetoeatschoollunch.view.license.LicenseActivity
import site.yoonsang.agetoeatschoollunch.view.main.adapter.VPAdapter
import site.yoonsang.agetoeatschoollunch.view.main.fragments.BreakfastFragment
import site.yoonsang.agetoeatschoollunch.view.main.fragments.DinnerFragment
import site.yoonsang.agetoeatschoollunch.view.main.fragments.LunchFragment
import site.yoonsang.agetoeatschoollunch.view.register.RegisterActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var sessionManager: SessionManager
    private val fragmentBreakfast by lazy { BreakfastFragment() }
    private val fragmentLunch by lazy { LunchFragment() }
    private val fragmentDinner by lazy { DinnerFragment() }
    private var presentDate = ""
    private var presentTime: Long = 0
    private var backBtnTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val schoolName = sessionManager.getSchoolName()

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.let {
            title = null
        }
        setClickSettingsMenu()
//        binding.mainToolbarTitle.text = ApplicationClass.sSharedPref.getString("schoolName", null)

        val now = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        val todayDate = simpleDateFormat.format(now)!!
        presentDate = todayDate
        presentTime = now

        setSelectDate(now)
//        getMeal(todayDate)
    }

    private fun setViewPager() {
        val tabTextList = arrayListOf("조식", "중식", "석식")
        val fragmentList = arrayListOf<Fragment>(fragmentBreakfast, fragmentLunch, fragmentDinner)
        val vpAdapter = VPAdapter(supportFragmentManager, lifecycle, fragmentList)
        binding.mainViewPager.adapter = vpAdapter
        TabLayoutMediator(binding.mainMealTimeTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
        binding.mainMealTimeTabLayout.selectTab(binding.mainMealTimeTabLayout.getTabAt(1))
    }

    private fun setDate() {
        val cal = Calendar.getInstance()
        val cYear = cal.get(Calendar.YEAR)
        val cMonth = cal.get(Calendar.MONTH)
        val cDay = cal.get(Calendar.DAY_OF_MONTH)

        val picker =
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selectDate =
                    "${year}${String.format("%02d", month + 1)}${String.format("%02d", dayOfMonth)}"
//                getMeal(selectDate)
                presentDate = selectDate
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val selectDateTime = calendar.timeInMillis
                setSelectDate(selectDateTime)
                presentTime = selectDateTime
            }, cYear, cMonth, cDay)
        picker.show()
    }

    private fun setSelectDate(time: Long) {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA)
        binding.mainTodayText.text = simpleDateFormat.format(time)
    }

    private fun setClickSettingsMenu() {
        binding.mainNavView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_set_allergy -> {
                    startActivityForResult(Intent(this, AllergyActivity::class.java), 100)
                }
                R.id.nav_change_school -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                }
                R.id.nav_faq -> {
                    startActivity(Intent(this, FaqActivity::class.java))
                }
                R.id.nav_open_source_license -> {
                    startActivity(Intent(this, LicenseActivity::class.java))
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.END)
        } else {
            val curTime = System.currentTimeMillis()
            val gapTime = curTime - backBtnTime

            if (gapTime in 0..2000) {
                super.onBackPressed()
            } else {
                backBtnTime = curTime
                Toast.makeText(this, "한번 더 누르면 종료됩니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_calendar_image -> {
                setDate()
            }
            R.id.main_settings_image -> {
                binding.mainDrawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                        binding.mainDrawerLayout.closeDrawer(GravityCompat.END)
                    }
//                    getMeal(presentDate)
                    setSelectDate(presentTime)
                }
            }
        }
    }
}