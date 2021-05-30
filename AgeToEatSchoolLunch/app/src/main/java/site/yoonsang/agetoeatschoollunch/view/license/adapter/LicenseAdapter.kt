package site.yoonsang.agetoeatschoollunch.view.license.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.model.License
import site.yoonsang.agetoeatschoollunch.databinding.ItemLicenseBinding

class LicenseAdapter(private val context: Context): RecyclerView.Adapter<LicenseAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ItemLicenseBinding
    private val data = arrayListOf<License>()

    init {
        data.add(License("Retrofit", "https://github.com/square/retrofit"))
        data.add(License("OkHttp", "https://github.com/square/okhttp"))
        data.add(License("Glide", "https://github.com/bumptech/glide"))
        data.add(License("Android-Spinkit", "https://github.com/ybq/Android-SpinKit"))
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = binding.itemLicenseName
        val url = binding.itemLicenseUrl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLicenseBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position].name
        holder.url.text = data[position].url
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].url))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}