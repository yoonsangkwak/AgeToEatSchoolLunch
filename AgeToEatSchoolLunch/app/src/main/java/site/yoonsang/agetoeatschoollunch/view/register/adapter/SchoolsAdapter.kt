package site.yoonsang.agetoeatschoollunch.view.register.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemSchoolBinding
import site.yoonsang.agetoeatschoollunch.model.SchoolInfo

class SchoolsAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<SchoolInfo, SchoolsAdapter.ViewHolder>(ITEM_COMPARATOR) {

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SchoolInfo>(){
            override fun areItemsTheSame(oldItem: SchoolInfo, newItem: SchoolInfo): Boolean {
                return oldItem.schoolCode == newItem.schoolCode
            }

            override fun areContentsTheSame(oldItem: SchoolInfo, newItem: SchoolInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schoolInfo: SchoolInfo) {
            binding.schoolInfo = schoolInfo
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(schoolInfo: SchoolInfo)
    }
}