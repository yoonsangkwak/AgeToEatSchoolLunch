package site.yoonsang.agetoeatschoollunch.view.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ActivityRegisterBinding
import site.yoonsang.agetoeatschoollunch.model.SchoolInfo
import site.yoonsang.agetoeatschoollunch.util.SessionManager
import site.yoonsang.agetoeatschoollunch.view.main.MainActivity
import site.yoonsang.agetoeatschoollunch.view.register.adapter.SchoolsAdapter
import site.yoonsang.agetoeatschoollunch.viewmodel.RegisterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), SchoolsAdapter.OnItemClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = SchoolsAdapter(this)
        binding.registerRecyclerView.adapter = adapter

        viewModel.schoolInfo.observe(this) {
            adapter.submitList(it)
        }

        viewModel.toastMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(schoolInfo: SchoolInfo) {
        sessionManager.setSchoolName(schoolInfo.schoolName)
        sessionManager.setSchoolCode(schoolInfo.schoolCode)
        sessionManager.setOfficeCode(schoolInfo.officeCode)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}