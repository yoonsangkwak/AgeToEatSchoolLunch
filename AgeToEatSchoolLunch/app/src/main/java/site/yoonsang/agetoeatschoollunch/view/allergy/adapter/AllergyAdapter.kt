package site.yoonsang.agetoeatschoollunch.view.allergy.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemAllergyBinding
import site.yoonsang.agetoeatschoollunch.model.Allergy

class AllergyAdapter(
    private val listener: OnItemUpdateListener
) :
    ListAdapter<Allergy, AllergyAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Allergy>() {
        override fun areItemsTheSame(oldItem: Allergy, newItem: Allergy): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Allergy, newItem: Allergy): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemAllergyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(allergy: Allergy) {
            binding.allergy = allergy
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        val newItem = Allergy(item.id, item.name, !item.checked)
                        listener.onItemUpdate(newItem)
                    }
                }
            }
            binding.itemAllergyCheckbox.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        val newItem = Allergy(item.id, item.name, !item.checked)
                        listener.onItemUpdate(newItem)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllergyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    interface OnItemUpdateListener {
        fun onItemUpdate(allergy: Allergy)
    }
}