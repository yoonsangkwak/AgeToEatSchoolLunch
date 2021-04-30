package site.yoonsang.agetoeatschoollunch.src.main.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.config.BaseFragment
import site.yoonsang.agetoeatschoollunch.databinding.FragmentDinnerBinding
import site.yoonsang.agetoeatschoollunch.src.main.fragments.adapter.MealAdapter
import site.yoonsang.agetoeatschoollunch.src.main.fragments.adapter.MealOriginAdapter
import site.yoonsang.agetoeatschoollunch.src.main.models.MealInfo
import java.io.BufferedReader
import java.io.InputStreamReader

class DinnerFragment : BaseFragment<FragmentDinnerBinding>(FragmentDinnerBinding::bind, R.layout.fragment_dinner) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments == null) {
            binding.dinnerNestedScrollView.visibility = View.GONE
            binding.dinnerNoDietText.visibility = View.VISIBLE
        } else {
            binding.dinnerNestedScrollView.visibility = View.VISIBLE
            binding.dinnerNoDietText.visibility = View.GONE

            val mealInfo = arguments?.getSerializable("meal") as MealInfo

            binding.dinnerCalText.text = mealInfo.calInfo
            val splitMenuList = mealInfo.mealMenu.split("<br/>")
            val menuList = mutableListOf<String>()
            val allergyList = mutableListOf<String>()
            for (splitMenu in splitMenuList) {
                val items = splitMenu.split(".")
                menuList.add(items[0])
                val temp = items.drop(1)
                val allergies = temp.dropLast(1)
                var allergy = ""
                for (i in allergies.indices) {
                    allergy += allergyIdToName(allergies[i])
                    if (i != allergies.size - 1) {
                        allergy += ", "
                    }
                }
                allergyList.add(allergy)
            }

            val originList = mealInfo.mealOrigin.split("<br/>")

            binding.dinnerMenuRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MealAdapter(context, menuList, allergyList)
            }

            binding.dinnerOriginRecyclerView.apply {
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