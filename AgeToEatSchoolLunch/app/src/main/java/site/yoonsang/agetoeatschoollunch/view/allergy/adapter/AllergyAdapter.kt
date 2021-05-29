package site.yoonsang.agetoeatschoollunch.view.allergy.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemAllergyBinding
import site.yoonsang.agetoeatschoollunch.model.Allergy

class AllergyAdapter :
    RecyclerView.Adapter<AllergyAdapter.ViewHolder>() {

    val allergies = mutableListOf<Allergy>()

    inner class ViewHolder(private val binding: ItemAllergyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val checkBox = binding.itemAllergyCheckbox

        fun bind(allergy: Allergy) {
            binding.allergy = allergy
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllergyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allergies[position])
        holder.checkBox.setOnClickListener {
            allergies[position].checked = !holder.checkBox.isChecked
        }
    }

    override fun getItemCount(): Int {
        return allergies.size
    }
}