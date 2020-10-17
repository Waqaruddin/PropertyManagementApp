package com.example.propertymanagementapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.propertymanagementapp.ui.auth.fragments.LandlordFragment
import com.example.propertymanagementapp.ui.auth.fragments.TenantFragment

class AdapterFragment(fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> LandlordFragment()
            1 -> TenantFragment()
            else -> LandlordFragment()
//            0 -> LandlordFragment()
//            else-> LandlordFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Landlord"
            1 -> "Tenant"
            else -> "Landlord"
//            0 -> "Landlord"
//            else -> "Landlord"
        }
    }

}