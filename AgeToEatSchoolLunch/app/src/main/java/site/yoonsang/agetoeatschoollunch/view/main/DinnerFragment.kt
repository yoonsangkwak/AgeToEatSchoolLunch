package site.yoonsang.agetoeatschoollunch.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.FragmentDinnerBinding
import site.yoonsang.agetoeatschoollunch.model.MealInfo
import site.yoonsang.agetoeatschoollunch.model.MealMenu
import site.yoonsang.agetoeatschoollunch.util.Constants
import site.yoonsang.agetoeatschoollunch.view.main.adapter.MealAdapter
import site.yoonsang.agetoeatschoollunch.viewmodel.AllergyViewModel
import site.yoonsang.agetoeatschoollunch.viewmodel.MainViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class DinnerFragment : Fragment() {

    private var _binding: FragmentDinnerBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()
    private val allergyViewModel by viewModels<AllergyViewModel>()

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

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val mealAdapter = MealAdapter(requireContext(), allergyViewModel, viewLifecycleOwner)
        binding.dinnerMenuRecyclerView.adapter = mealAdapter

        viewModel.mealDinner.observe(viewLifecycleOwner) { mealInfo ->

            val mealMenuList = getMealMenu(mealInfo)
            mealAdapter.submitList(mealMenuList)

            binding.dinnerOriginBtn.setOnClickListener {
                val originDialog = OriginDialog()
                val bundle = Bundle()
                bundle.putString("origin", mealInfo.mealOrigin)
                originDialog.arguments = bundle
                originDialog.show(childFragmentManager, Constants.ORIGIN_DIALOG)
            }

            binding.dinnerNutrientsBtn.setOnClickListener {
                val nutrientsDialog = NutrientsDialog()
                val bundle = Bundle()
                bundle.putString("nutrient", mealInfo.ntrInfo)
                nutrientsDialog.arguments = bundle
                nutrientsDialog.show(childFragmentManager, Constants.NUTRIENTS_DIALOG)
            }
        }
    }

    private fun getMealMenu(
        mealInfo: MealInfo
    ): MutableList<MealMenu> {
        val mealMenuList = mutableListOf<MealMenu>()

        val splitMenuList = mealInfo.mealMenu.split("<br/>")

        for (splitMenu in splitMenuList) {
            val menu = splitMenu.replace("[a-zA-Z0-9]|\\.".toRegex(), "")

            val removedMenuString = splitMenu.replace("[^\\d.]".toRegex(), "")
            val removedMenuList = removedMenuString.split(".")

            val allergyList = mutableListOf<String>()
            removedMenuList.forEach { if (it != "") allergyList.add(allergyIdToName(it)) }
            val allergy = allergyList.joinToString(", ")

            val mealMenu = MealMenu(menu, allergy)
            mealMenuList.add(mealMenu)
        }
        return mealMenuList
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
}