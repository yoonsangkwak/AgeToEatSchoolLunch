package site.yoonsang.agetoeatschoollunch.src.main.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemMenuBinding

class LunchAdapter(
    context: Context,
    private val menuList: List<String>,
    private val allergyList: MutableList<String>
) : RecyclerView.Adapter<LunchAdapter.ViewHolder>() {

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
        holder.allergy.text = allergyList[position]
    }

    override fun getItemCount(): Int = menuList.size
}