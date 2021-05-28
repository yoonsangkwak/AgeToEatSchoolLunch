package site.yoonsang.agetoeatschoollunch.view.register

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityRegisterBinding
import site.yoonsang.agetoeatschoollunch.viewmodel.RegisterViewModel

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.registerRecyclerView.visibility = View.INVISIBLE
        binding.registerNoResultText.visibility = View.VISIBLE

        binding.registerEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                return@setOnKeyListener true
            }
            false
        }

//        binding.registerSearchImage.setOnClickListener {
//            val schoolName = binding.registerEditText.text.toString()
//            showLoadingDialog(this)
//            RegisterPresenter(this).tryGetSchoolResponse(schoolName)
//        }
    }
}