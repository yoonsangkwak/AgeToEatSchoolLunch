package site.yoonsang.agetoeatschoollunch.src.main.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
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
            val splitMenuList = mealInfo.mealMenu.split("<br/>")
            val menuList = mutableListOf<String>()
            val allergyList = mutableListOf<String>()
            for (splitMenu in splitMenuList) {
                val allergiesString = splitMenu.replace("[^\\d.]".toRegex(), "")
                val allergies = allergiesString.split(".").dropLast(1)
                var allergy = ""
                for (idx in allergies.indices) {
                    allergy += if (idx != allergies.size - 1) {
                        "${allergyIdToName(allergies[idx])}, "
                    } else {
                        allergyIdToName(allergies[idx])
                    }
                }
                allergyList.add(allergy)

                val menu = splitMenu.replace("[a-zA-Z0-9]|\\.".toRegex(), "")
                menuList.add(menu)
            }

            val originList = mealInfo.mealOrigin.split("<br/>")

            binding.breakfastMenuRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MealAdapter(context, menuList, allergyList)
            }

            binding.breakfastOriginRecyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = MealOriginAdapter(context, originList)
            }
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
}