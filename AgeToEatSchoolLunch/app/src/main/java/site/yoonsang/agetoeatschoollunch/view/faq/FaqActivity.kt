package site.yoonsang.agetoeatschoollunch.view.faq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityFaqBinding
import site.yoonsang.agetoeatschoollunch.view.faq.adapter.FAQAdapter

@AndroidEntryPoint
class FaqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFaqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faq)

        binding.faqRecyclerView.adapter = FAQAdapter(this)
    }
}