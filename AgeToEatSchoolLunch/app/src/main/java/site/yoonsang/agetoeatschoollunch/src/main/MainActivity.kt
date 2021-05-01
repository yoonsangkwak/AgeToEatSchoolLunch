package site.yoonsang.agetoeatschoollunch.src.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
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

        val now = System.currentTimeMillis()
        val test = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        val mealDate = test.format(now)!!

        getMeal(mealDate)

        binding.mainSchoolNameText.text = ApplicationClass.sSharedPref.getString("schoolName", null)
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA)
        binding.mainTodayText.text = simpleDateFormat.format(now)
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
}