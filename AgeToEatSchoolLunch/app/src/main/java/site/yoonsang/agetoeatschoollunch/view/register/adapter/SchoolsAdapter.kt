package site.yoonsang.agetoeatschoollunch.view.register.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.databinding.ItemSchoolBinding
import site.yoonsang.agetoeatschoollunch.model.SchoolInfo

class SchoolsAdapter(val context: Context, private val data: List<SchoolInfo>) :
    RecyclerView.Adapter<SchoolsAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ItemSchoolBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolName = binding.itemSchoolName
        val officeName = binding.itemSchoolOfficeName
        val schoolLocation = binding.itemSchoolLocation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSchoolBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.schoolName.text = data[position].schoolName
        holder.officeName.text = data[position].officeName
        holder.schoolLocation.text = data[position].roadLocation

        holder.itemView.setOnClickListener {
//            ApplicationClass.sSharedPref.edit().putString("officeCode", data[position].officeCode).apply()
//            ApplicationClass.sSharedPref.edit().putString("schoolCode", data[position].schoolCode).apply()
//            ApplicationClass.sSharedPref.edit().putString("schoolName", data[position].schoolName).apply()
//            val intent = Intent(context, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            context.startActivity(intent)
//            (context as RegisterActivity).finish()
        }
    }

    override fun getItemCount(): Int = data.size
}