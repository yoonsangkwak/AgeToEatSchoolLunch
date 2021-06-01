package site.yoonsang.agetoeatschoollunch.view.faq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.model.FAQ
import site.yoonsang.agetoeatschoollunch.databinding.ItemFaqBinding

class FAQAdapter(context: Context) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    private val data = arrayListOf<FAQ>()

    init {
        data.add(
            FAQ(
                context.getString(R.string.question1),
                context.getString(R.string.answer1)
            )
        )
        data.add(
            FAQ(
                context.getString(R.string.question2),
                context.getString(R.string.answer2)
            )
        )
        data.add(
            FAQ(
                context.getString(R.string.question3),
                context.getString(R.string.answer3)
            )
        )
        data.add(
            FAQ(
                context.getString(R.string.question4),
                context.getString(R.string.answer4)
            )
        )
    }

    inner class ViewHolder(private val binding: ItemFaqBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(faq: FAQ) {
            binding.faq = faq
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFaqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}