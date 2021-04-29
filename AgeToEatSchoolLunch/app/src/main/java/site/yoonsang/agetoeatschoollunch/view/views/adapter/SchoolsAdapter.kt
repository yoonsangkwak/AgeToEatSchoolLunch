package site.yoonsang.agetoeatschoollunch.view.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemSchoolBinding
import site.yoonsang.agetoeatschoollunch.view.viewmodels.RegisterViewModel

class SchoolsAdapter(viewModel: RegisterViewModel): RecyclerView.Adapter<SchoolsAdapter.ViewHolder>() {

    private val viewModel = viewModel

    inner class ViewHolder(binding: ItemSchoolBinding): RecyclerView.ViewHolder(binding.root) {
        val binding = binding

        fun bind(viewModel: RegisterViewModel, position: Int) {
            binding.registerViewModel = viewModel
            binding.position = position
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSchoolBinding = ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemCount(): Int {
        return viewModel.getSchoolsItem().size
    }
}