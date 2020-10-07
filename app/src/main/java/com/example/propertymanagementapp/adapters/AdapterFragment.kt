package com.example.propertymanagementapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.propertymanagementapp.fragments.LandlordFragment
import com.example.propertymanagementapp.fragments.TenantFragment

class AdapterFragment(fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> LandlordFragment()
            1 -> TenantFragment()
            else -> LandlordFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Landlord"
            1 -> "Tenant"
            else -> "Landlord"
        }
    }

}