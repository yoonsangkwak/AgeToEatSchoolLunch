package site.yoonsang.agetoeatschoollunch.src.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter(fm: FragmentManager?, lifecycle: Lifecycle, private val items: ArrayList<Fragment>): FragmentStateAdapter(fm!!, lifecycle) {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}