package site.yoonsang.agetoeatschoollunch.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.DialogNutrientsBinding
import site.yoonsang.agetoeatschoollunch.view.main.adapter.MealOriginAdapter

@AndroidEntryPoint
class NutrientsDialog: DialogFragment() {

    private var _binding: DialogNutrientsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogNutrientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nutrient = arguments?.getString("nutrient")

        val adapter = MealOriginAdapter()
        adapter.submitList(nutrient?.split("<br/>"))
        binding.nutrientsRecyclerView.adapter = adapter

        binding.nutrientsCloseBtn.setOnClickListener {
            dismiss()
        }
    }
}