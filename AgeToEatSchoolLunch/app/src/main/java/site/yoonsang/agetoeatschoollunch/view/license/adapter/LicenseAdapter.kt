package site.yoonsang.agetoeatschoollunch.view.license.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.R
import site.yoonsang.agetoeatschoollunch.model.License
import site.yoonsang.agetoeatschoollunch.databinding.ItemLicenseBinding

class LicenseAdapter(private val context: Context): RecyclerView.Adapter<LicenseAdapter.ViewHolder>() {

    private val data = arrayListOf<License>()

    init {
        data.add(License(context.getString(R.string.license_name_1), context.getString(R.string.license_url_1)))
        data.add(License(context.getString(R.string.license_name_2), context.getString(R.string.license_url_2)))
        data.add(License(context.getString(R.string.license_name_3), context.getString(R.string.license_url_3)))
        data.add(License(context.getString(R.string.license_name_4), context.getString(R.string.license_url_4)))
    }

    inner class ViewHolder(private val binding: ItemLicenseBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(license: License) {
            binding.license = license
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLicenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].url))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}