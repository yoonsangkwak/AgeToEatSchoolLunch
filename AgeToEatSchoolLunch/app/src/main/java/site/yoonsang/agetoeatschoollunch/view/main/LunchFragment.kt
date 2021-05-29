package site.yoonsang.agetoeatschoollunch.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.FragmentLunchBinding
import site.yoonsang.agetoeatschoollunch.model.MealInfo
import site.yoonsang.agetoeatschoollunch.util.Constants
import site.yoonsang.agetoeatschoollunch.viewmodel.MainViewModel

@AndroidEntryPoint
class LunchFragment : Fragment() {

    private var _binding: FragmentLunchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lunch, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        if (arguments != null) {
            val mealInfo = requireArguments().getSerializable("meal") as MealInfo
            binding.lunchCalText.text = mealInfo.calInfo

            binding.lunchOriginBtn.setOnClickListener {
                val originDialog = OriginDialog()
                val bundle = Bundle()
                bundle.putString("origin", mealInfo.mealOrigin)
                originDialog.arguments = bundle
                originDialog.show(childFragmentManager, Constants.ORIGIN_DIALOG)
            }

            binding.lunchNutrientsBtn.setOnClickListener {
                val nutrientsDialog = NutrientsDialog()
                val bundle = Bundle()
                bundle.putString("nutrient", mealInfo.ntrInfo)
                nutrientsDialog.arguments = bundle
                nutrientsDialog.show(childFragmentManager, Constants.NUTRIENTS_DIALOG)
            }
        }
    }
}