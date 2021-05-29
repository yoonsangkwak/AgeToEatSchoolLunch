package site.yoonsang.agetoeatschoollunch.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.DialogOriginBinding
import site.yoonsang.agetoeatschoollunch.view.main.adapter.MealOriginAdapter

@AndroidEntryPoint
class OriginDialog : DialogFragment() {

    private var _binding: DialogOriginBinding? = null
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
        _binding = DialogOriginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val origin = arguments?.getString("origin")

        val adapter = MealOriginAdapter()
        adapter.submitList(origin?.split("<br/>"))
        binding.originRecyclerView.adapter = adapter

        binding.originCloseBtn.setOnClickListener {
            dismiss()
        }
    }
}