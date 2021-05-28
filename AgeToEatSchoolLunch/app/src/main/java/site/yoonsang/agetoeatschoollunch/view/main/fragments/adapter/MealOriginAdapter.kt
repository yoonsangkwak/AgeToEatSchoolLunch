package site.yoonsang.agetoeatschoollunch.view.main.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemOriginBinding

class MealOriginAdapter(context: Context, private val originList: List<String>): RecyclerView.Adapter<MealOriginAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ItemOriginBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val origin = binding.itemOriginName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemOriginBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.origin.text = originList[position]
    }

    override fun getItemCount(): Int = originList.size
}