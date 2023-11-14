package com.nori.itfetch.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nori.itfetch.fragment.AiFragment
import com.nori.itfetch.fragment.IotFragment
import com.nori.itfetch.fragment.TechnologyFragment
import com.nori.itfetch.fragment.BlockchainFragment

class SectionPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> TechnologyFragment()
            1 -> AiFragment()
            2 -> IotFragment()
            3 -> BlockchainFragment()
            else -> IotFragment()
        }
    }
}