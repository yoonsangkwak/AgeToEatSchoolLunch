package site.yoonsang.agetoeatschoollunch.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import site.yoonsang.agetoeatschoollunch.ApplicationClass
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityRegisterBinding
import site.yoonsang.agetoeatschoollunch.viewmodels.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel.viewInit(binding.registerRecyclerView)
        viewModel.school.observe(this, {
            val intent = Intent(this, MainActivity::class.java)
            ApplicationClass.sSharedPref.edit().putString("officeCode", it.officeCode).apply()
            ApplicationClass.sSharedPref.edit().putString("schoolCode", it.schoolCode).apply()
            ApplicationClass.sSharedPref.edit().putString("schoolName", it.schoolName).apply()
            startActivity(intent)
        })
        binding.registerViewModel = viewModel
        binding.lifecycleOwner = this

    }
}