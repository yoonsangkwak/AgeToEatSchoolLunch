package site.yoonsang.agetoeatschoollunch.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.FragmentBreakfastBinding
import site.yoonsang.agetoeatschoollunch.model.MealInfo
import site.yoonsang.agetoeatschoollunch.util.Constants
import site.yoonsang.agetoeatschoollunch.viewmodel.MainViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class BreakfastFragment : Fragment() {

    private var _binding: FragmentBreakfastBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_breakfast, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        if (arguments != null) {
            val mealInfo = requireArguments().getSerializable("meal") as MealInfo
            binding.breakfastCalText.text = mealInfo.calInfo

            binding.breakfastOriginBtn.setOnClickListener {
                val originDialog = OriginDialog()
                val bundle = Bundle()
                bundle.putString("origin", mealInfo.mealOrigin)
                originDialog.arguments = bundle
                originDialog.show(childFragmentManager, Constants.ORIGIN_DIALOG)
            }

            binding.breakfastNutrientsBtn.setOnClickListener {
                val nutrientsDialog = NutrientsDialog()
                val bundle = Bundle()
                bundle.putString("nutrient", mealInfo.ntrInfo)
                nutrientsDialog.arguments = bundle
                nutrientsDialog.show(childFragmentManager, Constants.NUTRIENTS_DIALOG)
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