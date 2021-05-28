package site.yoonsang.agetoeatschoollunch.view.main.fragments.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.databinding.ItemMenuBinding

class MealAdapter(
    private val context: Context,
    private val menuList: MutableList<String>,
    private val allergyList: MutableList<String>,
    private val myAllergyList: ArrayList<String>
) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ItemMenuBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuName = binding.itemMenuName
        val allergy = binding.itemMenuAllergy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMenuBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuName.text = menuList[position]
        spanString(holder.allergy, allergyList[position], myAllergyList)
    }

    override fun getItemCount(): Int = menuList.size

    private fun spanString(
        textView: TextView,
        allergyText: String,
        myAllergyList: ArrayList<String>
    ) {
        val spannableString = SpannableString(allergyText)
        for (myAllergy in myAllergyList) {
            if (allergyText.contains(myAllergy)) {
                val start = allergyText.indexOf(myAllergy)
                val end = start + myAllergy.length
                spannableString.setSpan(
                    ForegroundColorSpan(context.getColor(R.color.myAllergy)),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        textView.text = spannableString
    }
}