package site.yoonsang.agetoeatschoollunch.src.register

import android.os.Bundle
import android.view.KeyEvent
import androidx.recyclerview.widget.LinearLayoutManager
import site.yoonsang.agetoeatschoollunch.config.BaseActivity
import site.yoonsang.agetoeatschoollunch.databinding.ActivityRegisterBinding
import site.yoonsang.agetoeatschoollunch.src.register.adapter.SchoolsAdapter
import site.yoonsang.agetoeatschoollunch.src.register.models.SchoolResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate),
    RegisterContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.registerEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                binding.registerSearchImage.performClick()
                return@setOnKeyListener true
            }
            false
        }

        binding.registerSearchImage.setOnClickListener {
            val schoolName = binding.registerEditText.text.toString()
            showLoadingDialog(this)
            RegisterPresenter(this).tryGetSchoolResponse(schoolName)
        }
    }

    override fun getSchoolResponseSuccess(response: SchoolResponse) {
        dismissLoadingDialog()
        if (response.schoolResponseInfo != null) {
            val schoolList = response.schoolResponseInfo[1].schoolInfo
            binding.registerRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = SchoolsAdapter(context, schoolList)
            }
        }
    }

    override fun getSchoolResponseFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}