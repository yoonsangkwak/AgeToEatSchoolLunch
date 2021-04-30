package site.yoonsang.agetoeatschoollunch.src.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import site.yoonsang.agetoeatschoollunch.src.main.fragments.BreakfastFragment
import site.yoonsang.agetoeatschoollunch.src.main.fragments.DinnerFragment
import site.yoonsang.agetoeatschoollunch.src.main.fragments.LunchFragment

class VPAdapter(fm: FragmentManager?, lifecycle: Lifecycle): FragmentStateAdapter(fm!!, lifecycle) {

    private val items: ArrayList<Fragment> = ArrayList()

    init {
        items.add(BreakfastFragment())
        items.add(LunchFragment())
        items.add(DinnerFragment())
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}