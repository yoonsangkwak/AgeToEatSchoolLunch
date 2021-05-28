package site.yoonsang.agetoeatschoollunch.view.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.FragmentDinnerBinding
import site.yoonsang.agetoeatschoollunch.model.MealInfo
import site.yoonsang.agetoeatschoollunch.view.main.fragments.adapter.MealAdapter
import site.yoonsang.agetoeatschoollunch.view.main.fragments.adapter.MealOriginAdapter
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class DinnerFragment : Fragment() {

    private var _binding: FragmentDinnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dinner, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
            val menuList = mutableListOf<String>()
            val allergyList = mutableListOf<String>()
            setMenuPrettier(mealInfo, menuList, allergyList)
            val originList = mealInfo.mealOrigin.split("<br/>")
//            val myAllergyList = getMyAllergies()
//
//            binding.dinnerMenuRecyclerView.apply {
//                layoutManager = LinearLayoutManager(context)
//                adapter = MealAdapter(context, menuList, allergyList, myAllergyList)
//            }

            binding.dinnerOriginRecyclerView.apply {
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
        val assetManager = requireContext().resources.assets
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

//    private fun getMyAllergies(): ArrayList<String> {
//        val dbHelper = ApplicationClass.sDBHelper
//        val myAllergies = arrayListOf<String>()
//        for (allergy in dbHelper.selectAllData()) {
//            if (allergy.checked == 1) {
//                myAllergies.add(allergy.name)
//            }
//        }
//        return myAllergies
//    }
}