package site.yoonsang.agetoeatschoollunch.src.faq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemFaqBinding

class FAQAdapter(context: Context) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ItemFaqBinding
    private val data = arrayListOf<FAQ>()

    init {
        data.add(
            FAQ(
                "급식 정보를 어떻게 알 수 있는건가요?",
                "급식먹을나이에서 제공하는 식단 정보는 나이스 교육정보 개방 포털에서 제공하는 데이터를 통해서 제공됩니다."
            )
        )
        data.add(
            FAQ(
                "어플에 있는 식단과 실제 식단이 달라요.",
                "각 학교 사정에 따라서 식단은 변경될 수도 있습니다."
            )
        )
        data.add(
            FAQ(
                "우리 학교는 왜 검색되지 않는거죠?",
                "나이스에 학교정보가 등록되지 않으면 학교 정보들을 제공받기 어렵습니다."
            )
        )
        data.add(
            FAQ(
                "학교는 등록됐는데 식단 정보가 하나도 안떠요.",
                "중학교와 고등학교가 같은 급식실을 사용하는 경우에는 한 쪽으로만 식단 정보가 제공될 수 있습니다. " +
                        "\n이 경우에는 같은 급식실을 사용하는 학교로 등록해주세요!"
            )
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question = binding.itemFaqQuestionText
        val answer = binding.itemFaqAnswerText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFaqBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.question.text = data[position].question
        holder.answer.text = data[position].answer
    }

    override fun getItemCount(): Int = data.size
}

data class FAQ(
    val question: String,
    val answer: String
)