package site.yoonsang.agetoeatschoollunch.view.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ItemMenuBinding
import site.yoonsang.agetoeatschoollunch.model.Allergy
import site.yoonsang.agetoeatschoollunch.model.MealMenu
import site.yoonsang.agetoeatschoollunch.viewmodel.AllergyViewModel

class MealAdapter(
    private val context: Context,
    private val allergyViewModel: AllergyViewModel,
    private val lifecycleOwner: LifecycleOwner
) :
    ListAdapter<MealMenu, MealAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<MealMenu>() {
        override fun areItemsTheSame(oldItem: MealMenu, newItem: MealMenu): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MealMenu, newItem: MealMenu): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mealMenu: MealMenu) {
            allergyViewModel.allergies.observe(lifecycleOwner) {
                binding.mealMenu = mealMenu
                val spannableString = spanString(mealMenu.allergy, it)
                binding.spannableString = spannableString
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    private fun spanString(
        allergyText: String,
        myAllergyList: List<Allergy>
    ): SpannableString {
        val spannableString = SpannableString(allergyText)
        for (myAllergy in myAllergyList) {
            if (allergyText.contains(myAllergy.name) && myAllergy.checked) {
                val start = allergyText.indexOf(myAllergy.name)
                val end = start + myAllergy.name.length
                spannableString.setSpan(
                    ForegroundColorSpan(context.getColor(R.color.myAllergy)),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannableString
    }
}
