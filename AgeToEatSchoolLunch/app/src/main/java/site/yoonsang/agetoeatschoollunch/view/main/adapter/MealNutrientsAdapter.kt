package site.yoonsang.agetoeatschoollunch.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemNutrientBinding
import site.yoonsang.agetoeatschoollunch.databinding.ItemOriginBinding

class MealNutrientsAdapter: ListAdapter<String, MealNutrientsAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemNutrientBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nutrient: String) {
            binding.nutrient = nutrient
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNutrientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
}