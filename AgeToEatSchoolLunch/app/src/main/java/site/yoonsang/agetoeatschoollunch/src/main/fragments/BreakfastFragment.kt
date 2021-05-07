package site.yoonsang.agetoeatschoollunch.src.main.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.config.BaseFragment
import site.yoonsang.agetoeatschoollunch.databinding.FragmentBreakfastBinding
import site.yoonsang.agetoeatschoollunch.src.main.fragments.adapter.MealAdapter
import site.yoonsang.agetoeatschoollunch.src.main.fragments.adapter.MealOriginAdapter
import site.yoonsang.agetoeatschoollunch.src.main.models.MealInfo
import java.io.BufferedReader
import java.io.InputStreamReader

class BreakfastFragment : BaseFragment<FragmentBreakfastBinding>(FragmentBreakfastBinding::bind, R.layout.fragment_breakfast) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments == null) {
            binding.breakfastNestedScrollView.visibility = View.GONE
            binding.breakfastNoDietText.visibility = View.VISIBLE
        } else {
            binding.breakfastNestedScrollView.visibility = View.VISIBLE
            binding.breakfastNoDietText.visibility = View.GONE

            val mealInfo = arguments?.getSerializable("meal") as MealInfo

            binding.breakfastCalText.text = mealInfo.calInfo
            val menuList = mutableListOf<String>()
            val allergyList = mutableListOf<String>()
            setMenuPrettier(mealInfo, menuList, allergyList)
            val originList = mealInfo.mealOrigin.split("<br/>")
            val myAllergyList = getMyAllergies()

            binding.breakfastMenuRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MealAdapter(context, menuList, allergyList, myAllergyList)
            }

            binding.breakfastOriginRecyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = MealOriginAdapter(context, originList)
            }
        }
    }

    private fun setMenuPrettier(
        mealInfo: MealInfo,
        menuList: MutableList<String>,
        allergyList: MutableList<String>
    ) {
        val splitMenuList = mealInfo.mealMenu.split("<br/>")

        for (splitMenu in splitMenuList) {
            val menu = splitMenu.replace("[a-zA-Z0-9]|\\.".toRegex(), "")
            menuList.add(menu)

            val removedMenuString = splitMenu.replace("[^\\d.]".toRegex(), "")
            val removedMenuList = removedMenuString.split(".")

            val allergy = mutableListOf<String>()
            removedMenuList.forEach { if (it != "") allergy.add(allergyIdToName(it)) }
            allergyList.add(allergy.joinToString(", "))
        }
    }

    private fun allergyIdToName(allergyId: String): String {
        val assetManager = context!!.resources.assets
        val inputStream = assetManager.open("jsons/allergy.json")
        val isr = InputStreamReader(inputStream)
        val reader = BufferedReader(isr)

        val buffer = StringBuffer()
        var line = reader.readLine()
        while (line != null) {
            buffer.append(line + "\n")
            line = reader.readLine()
        }
        val jsonData = buffer.toString()

        val jsonObject = JSONObject(jsonData)
        return jsonObject.optString(allergyId, "")
    }

    private fun getMyAllergies(): ArrayList<String> {
        val dbHelper = ApplicationClass.sDBHelper
        val myAllergies = arrayListOf<String>()
        for (allergy in dbHelper.selectAllData()) {
            if (allergy.checked == 1) {
                myAllergies.add(allergy.name)
            }
        }
        return myAllergies
    }
}