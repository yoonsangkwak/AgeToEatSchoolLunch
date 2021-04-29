package site.yoonsang.agetoeatschoollunch.view.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityRegisterBinding
import site.yoonsang.agetoeatschoollunch.view.viewmodels.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel.viewInit(binding.registerRecyclerView)
        binding.registerViewModel = viewModel
        binding.lifecycleOwner = this
    }
}