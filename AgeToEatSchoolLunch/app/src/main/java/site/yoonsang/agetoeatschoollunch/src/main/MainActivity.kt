package site.yoonsang.agetoeatschoollunch.src.main

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.databinding.ActivityMainBinding
import site.yoonsang.agetoeatschoollunch.src.main.adapter.VPAdapter
import site.yoonsang.agetoeatschoollunch.src.main.fragments.BreakfastFragment
import site.yoonsang.agetoeatschoollunch.src.main.fragments.DinnerFragment
import site.yoonsang.agetoeatschoollunch.src.main.fragments.LunchFragment
import site.yoonsang.agetoeatschoollunch.src.main.models.MealResponse
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MainContract.View {

    private val fragmentBreakfast by lazy { BreakfastFragment() }
    private val fragmentLunch by lazy { LunchFragment() }
    private val fragmentDinner by lazy { DinnerFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainSchoolNameText.text = ApplicationClass.sSharedPref.getString("schoolName", null)

        val now = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        val todayDate = simpleDateFormat.format(now)!!

        setSelectDate(now)
        getMeal(todayDate)

        binding.mainCalendarImage.setOnClickListener {
            setDate()
        }
    }

    override fun getMealResponseSuccess(response: MealResponse) {
        dismissLoadingDialog()
        if (response.mealResponseInfo != null) {
            for (meal in response.mealResponseInfo[1].mealInfo) {
                when (meal.mealType) {
                    "조식" -> {
                        val bundle = Bundle()
                        bundle.putSerializable("meal", meal)
                        fragmentBreakfast.arguments = bundle
                    }
                    "중식" -> {
                        val bundle = Bundle()
                        bundle.putSerializable("meal", meal)
                        fragmentLunch.arguments = bundle
                    }
                    "석식" -> {
                        val bundle = Bundle()
                        bundle.putSerializable("meal", meal)
                        fragmentDinner.arguments = bundle
                    }
                }
            }
        } else {
            fragmentBreakfast.arguments = null
            fragmentLunch.arguments = null
            fragmentDinner.arguments = null
        }
        setViewPager()
    }

    override fun getMealResponseFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun getMeal(date: String) {
        val officeCode = ApplicationClass.sSharedPref.getString("officeCode", null)
        val schoolCode = ApplicationClass.sSharedPref.getString("schoolCode", null)
        showLoadingDialog(this)
        MainPresenter(this).tryGetMealResponse(officeCode!!, schoolCode!!, date)
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
            DatePickerDialog(this, { view, year, month, dayOfMonth ->
                val selectDate =
                    "${year}${String.format("%02d", month + 1)}${String.format("%02d", dayOfMonth)}"
                getMeal(selectDate)
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val selectDateTime = calendar.timeInMillis
                setSelectDate(selectDateTime)
            }, cYear, cMonth, cDay)
        picker.show()
    }

    private fun setSelectDate(time: Long) {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA)
        binding.mainTodayText.text = simpleDateFormat.format(time)
    }
}