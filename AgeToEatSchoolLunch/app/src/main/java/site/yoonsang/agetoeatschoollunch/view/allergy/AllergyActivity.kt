package site.yoonsang.agetoeatschoollunch.view.allergy

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityAllergyBinding
import site.yoonsang.agetoeatschoollunch.model.Allergy
import site.yoonsang.agetoeatschoollunch.view.allergy.adapter.AllergyAdapter
import site.yoonsang.agetoeatschoollunch.viewmodel.AllergyViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class AllergyActivity : AppCompatActivity(), AllergyAdapter.OnItemUpdateListener {

    private lateinit var binding: ActivityAllergyBinding
    private val viewModel by viewModels<AllergyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_allergy)

        val allergyAdapter = AllergyAdapter(this)
        binding.allergyRecyclerView.adapter = allergyAdapter

        viewModel.allergies.observe(this) {
            if (it != null) {
                if (it.isEmpty()) {
                    getAllergyList().forEach { allergy ->
                        viewModel.insert(allergy)
                    }
                }
                allergyAdapter.submitList(it)
            } else {
                Toast.makeText(this, "오류", Toast.LENGTH_SHORT).show()
            }
        }

        binding.allergyOkButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onItemUpdate(allergy: Allergy) {
        viewModel.update(allergy)
    }

    private fun getAllergyList(): MutableList<Allergy> {
        val allergyList = mutableListOf<Allergy>()
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
        return allergyList
    }
}