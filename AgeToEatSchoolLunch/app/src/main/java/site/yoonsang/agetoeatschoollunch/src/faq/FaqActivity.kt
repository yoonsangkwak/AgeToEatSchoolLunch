package site.yoonsang.agetoeatschoollunch.src.faq

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.databinding.ActivityFaqBinding
import site.yoonsang.agetoeatschoollunch.src.faq.adapter.FAQAdapter

class FaqActivity : BaseActivity<ActivityFaqBinding>(ActivityFaqBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.faqRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FAQAdapter(context)
        }
    }
}