package site.yoonsang.agetoeatschoollunch.view.allergy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemAllergyBinding

//class AllergyAdapter(context: Context, dbHelper: DBHelper) :
//    RecyclerView.Adapter<AllergyAdapter.ViewHolder>() {
//
//    private val inflater =
//        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    private lateinit var binding: ItemAllergyBinding
//    val data = dbHelper.selectAllData()
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val name = binding.itemAllergyName
//        val checkbox = binding.itemAllergyCheckbox
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        binding = ItemAllergyBinding.inflate(inflater, parent, false)
//        return ViewHolder(binding.root)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.name.text = data[position].name
//        holder.checkbox.isChecked = data[position].checked != 0
//        holder.itemView.setOnClickListener {
//            holder.checkbox.isChecked = !holder.checkbox.isChecked
//            if (data[position].checked == 0) {
//                data[position].checked = 1
//            } else {
//                data[position].checked = 0
//            }
//        }
//    }
//
//    override fun getItemCount(): Int = data.size
//}