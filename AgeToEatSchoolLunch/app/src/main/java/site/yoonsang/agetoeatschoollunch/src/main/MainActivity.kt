package site.yoonsang.agetoeatschoollunch.src.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.databinding.ActivityMainBinding
import site.yoonsang.agetoeatschoollunch.src.main.adapter.VPAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabTextList = arrayListOf("조식", "중식", "석식")

        val vpAdapter = VPAdapter(supportFragmentManager, lifecycle)
        binding.mainViewPager.adapter = vpAdapter

        TabLayoutMediator(binding.mainMealTimeTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
        binding.mainMealTimeTabLayout.selectTab(binding.mainMealTimeTabLayout.getTabAt(1))

        binding.mainSchoolNameText.text = ApplicationClass.sSharedPref.getString("schoolName", null)
        val now = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA)
        binding.mainTodayText.text = simpleDateFormat.format(now)
    }
}