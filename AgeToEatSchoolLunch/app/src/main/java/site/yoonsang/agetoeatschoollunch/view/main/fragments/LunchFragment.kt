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
import site.yoonsang.agetoeatschoollunch.databinding.FragmentLunchBinding
import site.yoonsang.agetoeatschoollunch.model.MealInfo
import site.yoonsang.agetoeatschoollunch.view.main.fragments.adapter.MealAdapter
import site.yoonsang.agetoeatschoollunch.view.main.fragments.adapter.MealOriginAdapter
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class LunchFragment : Fragment() {

    private var _binding: FragmentLunchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lunch, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments == null) {
            binding.lunchNestedScrollView.visibility = View.GONE
            binding.lunchNoDietText.visibility = View.VISIBLE
        } else {
            binding.lunchNestedScrollView.visibility = View.VISIBLE
            binding.lunchNoDietText.visibility = View.GONE

            val mealInfo = arguments?.getSerializable("meal") as MealInfo
            binding.lunchCalText.text = mealInfo.calInfo
            val menuList = mutableListOf<String>()
            val allergyList = mutableListOf<String>()
            setMenuPrettier(mealInfo, menuList, allergyList)
            val originList = mealInfo.mealOrigin.split("<br/>")
//            val myAllergyList = getMyAllergies()
//
//            binding.lunchMenuRecyclerView.apply {
//                layoutManager = LinearLayoutManager(context)
//                adapter = MealAdapter(context, menuList, allergyList, myAllergyList)
//            }

            binding.lunchOriginRecyclerView.apply {
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
        // <br/> 태그 단위로 메뉴를 리스트로 분할
        // ex) [···, 부추호박전.1.5.6., ···]
        val splitMenuList = mealInfo.mealMenu.split("<br/>")

        for (splitMenu in splitMenuList) {
            // 메뉴에 포함된 영어, 숫자, .(점)을 빈 문자열로 변환 후 메뉴 리스트에 추가
            // ex) 부추호박전
            val menu = splitMenu.replace("[a-zA-Z0-9]|\\.".toRegex(), "")
            menuList.add(menu)

            // 메뉴에 포함된 문자들을 빈 문자열로 변환해서 알레르기 숫자와 .(점)만 남도록 함
            // ex) .1.5.6.
            val removedMenuString = splitMenu.replace("[^\\d.]".toRegex(), "")
            // .(점)을 단위로 리스트로 분할
            // ex) ["", "1", "5", "6", ""]
            val removedMenuList = removedMenuString.split(".")

            // 빈 문자열을 제외한 알레르기 숫자들을 이름으로 변환 후 알레르기 리스트에 추가
            // ex) "난류, 대두, 밀"
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