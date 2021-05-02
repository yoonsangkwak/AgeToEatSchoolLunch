package site.yoonsang.agetoeatschoollunch.src.allergy

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.localData.Allergy
import site.yoonsang.agetoeatschoollunch.databinding.ActivityAllergyBinding
import site.yoonsang.agetoeatschoollunch.src.allergy.adapter.AllergyAdapter
import java.io.BufferedReader
import java.io.InputStreamReader

class AllergyActivity : BaseActivity<ActivityAllergyBinding>(ActivityAllergyBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = ApplicationClass.sDBHelper
        if (dbHelper.selectAllData().isEmpty()) {
            for (i in 1..18) {
                dbHelper.insertData(Allergy(allergyIdToName(i.toString())))
            }
        }

        val allergyAdapter = AllergyAdapter(this, dbHelper)

        binding.allergyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allergyAdapter
        }

        binding.allergyOkButton.setOnClickListener {
            for (allergy in allergyAdapter.data) {
                dbHelper.updateData(allergy)
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
}