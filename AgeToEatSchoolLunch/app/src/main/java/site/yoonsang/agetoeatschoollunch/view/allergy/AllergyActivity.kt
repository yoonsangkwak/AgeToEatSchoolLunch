package site.yoonsang.agetoeatschoollunch.view.allergy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityAllergyBinding
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class AllergyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllergyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_allergy)

//        val dbHelper = ApplicationClass.sDBHelper
//        if (dbHelper.selectAllData().isEmpty()) {
//            for (i in 1..18) {
//                dbHelper.insertData(Allergy(allergyIdToName(i.toString())))
//            }
//        }

//        val allergyAdapter = AllergyAdapter(this, dbHelper)
//
//        binding.allergyRecyclerView.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = allergyAdapter
//        }
//
//        binding.allergyOkButton.setOnClickListener {
//            for (allergy in allergyAdapter.data) {
//                dbHelper.updateData(allergy)
//            }
//            val intent = Intent()
//            intent.putExtra("changeAllergy", true)
//            setResult(Activity.RESULT_OK, intent)
//            finish()
//        }
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