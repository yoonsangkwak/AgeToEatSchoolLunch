package site.yoonsang.agetoeatschoollunch.view.allergy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityAllergyBinding
import site.yoonsang.agetoeatschoollunch.model.Allergy
import site.yoonsang.agetoeatschoollunch.view.allergy.adapter.AllergyAdapter
import site.yoonsang.agetoeatschoollunch.viewmodel.MainViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class AllergyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllergyBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_allergy)

        val allergyAdapter = AllergyAdapter()
        getAllergyList(allergyAdapter.allergies)
        binding.allergyRecyclerView.adapter = allergyAdapter

        binding.allergyOkButton.setOnClickListener {
            for (allergy in allergyAdapter.allergies) {
                if (allergy.checked) {
                    viewModel.insert(allergy)
                } else {
                    if (viewModel.allergies.value?.contains(allergy) == true) {
                        viewModel.delete(allergy)
                    }
                }
            }
            finish()
        }
    }

    private fun allergyIdToName(allergyId: String): String {
        val assetManager = resources.assets
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

    private fun getAllergyList(allergyList: MutableList<Allergy>) {
        val assetManager = resources.assets
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
        for (i in 1..jsonObject.length()) {
            val allergy = Allergy(i.toString(), jsonObject[i.toString()] as String)
            allergyList.add(allergy)
        }
    }
}