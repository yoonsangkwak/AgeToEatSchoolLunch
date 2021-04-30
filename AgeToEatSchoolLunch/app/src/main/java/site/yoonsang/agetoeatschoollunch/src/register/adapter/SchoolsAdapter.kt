package site.yoonsang.agetoeatschoollunch.src.register.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.config.ApplicationClass
import site.yoonsang.agetoeatschoollunch.databinding.ItemSchoolBinding
import site.yoonsang.agetoeatschoollunch.src.main.MainActivity
import site.yoonsang.agetoeatschoollunch.src.register.models.SchoolInfoRow

class SchoolsAdapter(val context: Context, private val data: List<SchoolInfoRow>) :
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
            ApplicationClass.sSharedPref.edit().putString("officeCode", data[position].officeCode).apply()
            ApplicationClass.sSharedPref.edit().putString("schoolCode", data[position].schoolCode).apply()
            ApplicationClass.sSharedPref.edit().putString("schoolName", data[position].schoolName).apply()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}