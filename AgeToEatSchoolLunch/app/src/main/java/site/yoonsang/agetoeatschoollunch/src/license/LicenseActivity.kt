package site.yoonsang.agetoeatschoollunch.src.license

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.databinding.ActivityLicenseBinding
import site.yoonsang.agetoeatschoollunch.src.license.adapter.LicenseAdapter

class LicenseActivity : BaseActivity<ActivityLicenseBinding>(ActivityLicenseBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.licenseRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = LicenseAdapter(context)
        }
    }
}