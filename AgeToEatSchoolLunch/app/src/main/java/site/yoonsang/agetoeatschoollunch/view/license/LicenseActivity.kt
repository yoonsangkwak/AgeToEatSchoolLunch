package site.yoonsang.agetoeatschoollunch.view.license

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.databinding.ActivityLicenseBinding
import site.yoonsang.agetoeatschoollunch.view.license.adapter.LicenseAdapter

@AndroidEntryPoint
class LicenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLicenseBinding.inflate(layoutInflater)

        binding.licenseRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = LicenseAdapter(context)
        }
    }
}